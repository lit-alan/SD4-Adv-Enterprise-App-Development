package com.sd4.controller;

import com.sd4.model.Book;
import com.sd4.service.BookService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alan.Ryan
 */
@RestController
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @GetMapping("/doit")
    @ResponseBody
    public String doIt(@RequestHeader(value = "test", defaultValue = "default") String test) {
        return "do it " + test;
    }
        
    @GetMapping("")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }
    @GetMapping("{id}")
    public ResponseEntity<Book> getBookByID(@PathVariable long id) {

        Optional<Book> b = bookService.findOne(id);
        if (!b.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(b.get());
        }
    }
    
    @GetMapping("/byauthor/{id}")
    public List<Book> getAllBooksByAuthor(@PathVariable("id") Long id) {

        List<Book> bList = bookService.findBooksByAuthor(id);
        return bList;

    }
        
    @PostMapping("")
    public ResponseEntity add(@Valid @RequestBody Book b) {
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

