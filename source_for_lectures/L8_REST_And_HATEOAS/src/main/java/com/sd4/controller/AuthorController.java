package com.sd4.controller;

import com.sd4.model.Author;
import com.sd4.service.AuthorService;
import com.sd4.service.BookService;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    
    @Autowired
    private BookService bookService;

//    @GetMapping("")
//    public List<Author> getAll() {
//        return authorService.findAll();
//    }
//    
//    @GetMapping(value = "/{id}")
//    public Author getOne(@PathVariable long id) {
//       return authorService.findOne(id).get();
//    }
    
    
    @GetMapping(value = "", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Author>>  getAll() {
        List<Author> alist =  authorService.findAll();
        
        if (alist.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        else
            return ResponseEntity.ok(alist);
    }
    
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}  )
    public ResponseEntity<Author> getOne(@PathVariable long id) {
        Optional<Author> a = authorService.findOne(id);
        if (!a.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
        return ResponseEntity.ok(a.get());
        } 
    }
  
    
    @GetMapping("/count")
    public long getCount() {
        return authorService.count();
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id) {
        authorService.deleteByID(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @PostMapping(value = "",  consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity add(@RequestBody Author a) {
        authorService.saveAuthor(a);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity edit(@RequestBody Author a) {
        authorService.saveAuthor(a);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    
//    @GetMapping(value = "/hateoas/{authorID}", produces = MediaTypes.HAL_JSON_VALUE)
//    public ResponseEntity<Author> getAuthorWithHATEOAS(@PathVariable long authorID) {
//        Optional<Author> a = authorService.findOne(authorID);
//         if (!a.isPresent()) {
//           return new ResponseEntity(HttpStatus.NOT_FOUND);
//         }
//         else {
//            Link selfLink = new Link("http://localhost:8081/authors/" + authorID).withSelfRel();
//            a.get().add(selfLink);
//            return ResponseEntity.ok(a.get());
//         }
//    }
    @GetMapping(value = "/hateoas/{authorID}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Author> getAuthorWithHATEOAS(@PathVariable long authorID) {
        Optional<Author> a = authorService.findOne(authorID);
         if (!a.isPresent()) {
           return new ResponseEntity(HttpStatus.NOT_FOUND);
         }
         else {
            Link selfLink = linkTo(methodOn(AuthorController.class).getOne(authorID)).withSelfRel();
            a.get().add(selfLink);
            return ResponseEntity.ok(a.get());
         }
    }
        
    @GetMapping(value = "/hateoas", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<Author> getAllAuthorsHATEOAS() {

        List<Author> aList = authorService.findAll();

        for (final Author a : aList) {
            long id = a.getAuthorID();
            Link selfLink = linkTo(AuthorController.class).slash(id).withSelfRel();
            a.add(selfLink);

            if (bookService.findBooksByAuthor(a.getAuthorID()).size() > 0) {
                Link booksLink = linkTo(methodOn(BookController.class)
                        .getAllBooksByAuthor(id)).withRel("allBooks");
                a.add(booksLink);

            }

        }

        Link link = linkTo(AuthorController.class).withSelfRel();
        CollectionModel<Author> result = CollectionModel.of(aList, link);

        return result;
    }
    
   


}//end class


