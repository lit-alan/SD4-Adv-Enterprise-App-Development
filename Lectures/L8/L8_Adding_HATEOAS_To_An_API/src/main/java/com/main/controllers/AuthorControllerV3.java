
package com.main.controllers;

import com.main.model.Author;
import com.main.service.AuthorService;
import com.main.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v3/")
public class AuthorControllerV3 {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private ResourceLoader resourceLoader;


    //the first two methods here bring HATEOAS support to this version of the API
    @GetMapping(value = "/authors/hateoas/{authorID}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Author> getAuthorWithHATEOAS(@PathVariable long authorID) {
        Optional<Author> a = authorService.findOne(authorID);
        if (!a.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            Link selfLink = linkTo(methodOn(AuthorControllerV3.class).getOne(authorID)).withSelfRel();
            a.get().add(selfLink);
            return ResponseEntity.ok(a.get());
        }
    }

    @GetMapping(value = "authors/hateoas", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<Author> getAllAuthorsHATEOAS() {

        List<Author> aList = authorService.findAll();

        for (final Author a : aList) {
            long id = a.getAuthorID();
            Link selfLink = linkTo(methodOn(AuthorControllerV3.class).getOne(id)).withSelfRel();
            a.add(selfLink);

            if (bookService.findBooksByAuthor(a.getAuthorID()).size() > 0) {
                Link booksLink = linkTo(methodOn(BookControllerV1.class)
                        .getAllBooksByAuthor(id)).withRel("allBooks");
                a.add(booksLink);
            }
        }

        Link link = linkTo(methodOn(AuthorControllerV3.class).getAll()).withSelfRel();
        CollectionModel<Author> result = CollectionModel.of(aList, link);

        return result;
    }

    // Paginated request - new to this version
    @GetMapping(value = "authors/paginated", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getAllAuthors(Pageable pageable) {
        Page<Author> authors = authorService.findAllWithPagination(pageable);
        return ResponseEntity.ok(authors);
    }


    // Additional endpoint to filtering filtering - new to this versoin
    @GetMapping(value = "authors/filtered", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<Author>> getAllAuthors(
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) Integer yearBorn,
            @RequestParam(required = false) String search,
            Pageable pageable) {

        if (lastName != null) {
            return ResponseEntity.ok(authorService.findAllByLastName(lastName, pageable));
        } else if (yearBorn != null) {
            return ResponseEntity.ok(authorService.findAllByYearBorn(yearBorn, pageable));
        } else if (search != null) {
            // Assuming 'search' queries both first and last names
            return ResponseEntity.ok(authorService.searchAuthors(search, search, pageable));
        } else {
            return ResponseEntity.ok(authorService.findAllWithPagination(pageable));
        }
    }

    //there are slight amendments to the following three methods in this version in that the ResponseEntity they return
    //has been changed slighty to give a more descriptive response on the action taken
    @DeleteMapping("/authors/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        authorService.deleteByID(id);
        return ResponseEntity.ok("Author entity has been deleted");
    }


    @PostMapping(value = "/authors", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity add(@RequestBody Author a) {
        authorService.saveAuthor(a);
        return ResponseEntity.ok("Author entity is valid and has been added");
    }

    @PutMapping(value = "/authors", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity edit(@RequestBody Author a) {
        authorService.saveAuthor(a);
        return ResponseEntity.ok("Author entity is valid and has been amended");
    }


    //The following methods are all copied from v2 of the AuthorController
    //I am following the practice of keeping each version self-contained, which means there can be a significant amount of
    // duplication. This is one of the trade-offs of versioning by URL (other options exist) and maintaining multiple active versions of an API.
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


    //I am leaving this endpoint in the code base to insure backward compatability for consumers.
    //The paginated endpoint authors/paginated essentially offers the same functionality but with pagination
    @GetMapping(value = "/authors", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Author> getAll() {
        return authorService.findAll();
    }

    @GetMapping(value = "/authors/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Author> getOne(@PathVariable Long id) {
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