package l4.source.sd4.controllers;

import jakarta.validation.Valid;
import l4.source.sd4.model.Book;
import l4.source.sd4.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookRestController {

    @Autowired
    private BookService bookService;

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

    
    @GetMapping("/author/{id}")
    public List<Book> getAllBooksByAuthor(@PathVariable("id") Long id) {

        List<Book> bList = bookService.findBooksByAuthor(id);
        return bList;

    }

    @GetMapping("")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }




}
