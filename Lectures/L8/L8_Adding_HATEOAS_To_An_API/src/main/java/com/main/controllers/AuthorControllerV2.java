
package com.main.controllers;

import com.main.model.Author;
import com.main.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2/")
public class AuthorControllerV2 {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping(value = "/authors/{id}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> getAuthorImage(@PathVariable Long id) {
        Resource resource = resourceLoader.getResource("classpath:/static/assets/images/" + id + ".png");
        if (resource.exists() && resource.isReadable()) {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(resource);
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @GetMapping(value = "/authors/images-zip", produces = "application/zip")
    public ResponseEntity<Resource> downloadImagesZip() throws IOException {
        Resource resource = new ClassPathResource("static/assets/images/all.zip");

        String filename = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();
        // Set the correct content type and disposition for a downloadable file
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(filename).build());

        //This line not strictly necessary for the functionality of downloading a zip file, as the produces
        //attribute in the @GetMapping annotation already specifies that the endpoint produces content of type application/zip
        //Setting the Content-Type header explicitly in the response can be beneficial for clarity.
        //It also serves as a form of documentation within the code, making it clear to other developers what the expected
        //type of the response body is.
        headers.setContentType(MediaType.parseMediaType("application/zip"));

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }



    @GetMapping(value = "/authors", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Author> getAll() {
        return authorService.findAll();
    }

    @GetMapping(value = "/authors/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Author> getOne(@PathVariable long id) {
       Optional<Author> o =  authorService.findOne(id);
       
       if (!o.isPresent()) 
            return new ResponseEntity(HttpStatus.NOT_FOUND);
         else 
            return ResponseEntity.ok(o.get());
    }
    
    @GetMapping("/authors/count")
    public long getCount() {
        return authorService.count();
    }
    
    @DeleteMapping("/authors/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        authorService.deleteByID(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/authors", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity add(@RequestBody Author a) {
        authorService.saveAuthor(a);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(value = "/authors", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity edit(@RequestBody Author a) {
        authorService.saveAuthor(a);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/authors/findby/firstname/prefix/{prefix}")
    public List<Author> getAllByPrefix(@PathVariable String prefix) {
        return authorService.findByFirstNameNameStartingWith(prefix);
    }

    @GetMapping("/authors/findby/firstname/infix/{infix}")
    public List<Author> getAllByInfix(@PathVariable String infix) {
        return authorService.findByfirstNameContaining(infix);
    }

    @GetMapping("/authors/findby/lastname/suffix/{suffix}")
    public List<Author> findBylastNameEndingWith(@PathVariable String suffix) {
        return authorService.findByFirstNameNameEndingWith(suffix);
    }

    @GetMapping("/authors/bornbetween/{start}/{end}")
    public List<Author> getBornBetween(@PathVariable Integer start, @PathVariable Integer end) {
        return authorService.findAuthorByYearBornBetweenOrderByFirstName(start, end);
    }

    @GetMapping("/authors/bornBefore/{yearBorn}")
    public List<Author> findByyearBornLessThanEqual(@PathVariable int yearBorn) {
        return authorService.findByyearBornLessThanEqual(yearBorn);
    }

    @GetMapping("/authors/avg/yearborn/")
    public int getAvgYearBorn() {
        return authorService.getAvgYearBorn();
    }

}
