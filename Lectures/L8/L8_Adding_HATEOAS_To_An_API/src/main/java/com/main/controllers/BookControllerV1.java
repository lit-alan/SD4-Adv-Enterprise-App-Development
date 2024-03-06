package com.main.controllers;

import com.main.model.Book;
import com.main.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Alan.Ryan
 */
@RestController
@RequestMapping("/api/v1/")
public class BookControllerV1 {
    
    @Autowired
    private BookService bookService;

    @GetMapping(value ="books/byauthor/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public List<Book> getAllBooksByAuthor(@PathVariable("id") Long id) {

        List<Book> bList = bookService.findBooksByAuthor(id);
        return bList;

    }



    @GetMapping("books/")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("books/{id}")
    public ResponseEntity<Book> getBookByID(@PathVariable long id) {

        Optional<Book> b = bookService.findOne(id);
        if (!b.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(b.get());
        }
    }

        
    @PostMapping("books")
    public ResponseEntity add(@Valid @RequestBody Book b) {
        System.out.println(b);
        bookService.saveBook(b);
        return ResponseEntity.ok("Book entity is valid and has been added");
    }
   
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}

