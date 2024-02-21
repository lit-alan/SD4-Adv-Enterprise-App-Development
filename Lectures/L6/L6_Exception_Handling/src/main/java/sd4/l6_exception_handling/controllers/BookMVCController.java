package sd4.l6_exception_handling.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import sd4.l6_exception_handling.exceptions.BookNotFoundException;
import sd4.l6_exception_handling.model.Book;
import sd4.l6_exception_handling.service.BookService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookMVCController {
   
    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public ModelAndView addAnAgent(@Valid @ModelAttribute("aBook") Book b, BindingResult result) {
        if (result.hasErrors())
            return new ModelAndView("/addBook");

        bookService.saveBook(b);
       //return new ModelAndView("/viewAll", "books", bookService.findAll());
       return new ModelAndView("redirect:/book");
    }


    @GetMapping("/title/{id}")
    @ResponseBody
    public String getTitleByID(@PathVariable("id") long id) {
        Optional<Book> o = bookService.findOne(id);
        if (o.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The requested title could not be found, please try again", new Exception());
        } else {
            return o.get().getTitle();
        }
    }

    @GetMapping("/add")
    public ModelAndView displayAddForm() {
        return new ModelAndView("/addBook", "aBook", new Book());
    }

    @RequestMapping("")
    public ModelAndView getAllBooks() {
        return new ModelAndView("/viewAll", "books", bookService.findAll());
    }


    @GetMapping("/edit/{id}")
    public ModelAndView displayEditForm(@PathVariable("id") long id){

       if (!bookService.findOne(id).isPresent())
            throw new BookNotFoundException();
        else
            return new ModelAndView("/editBook", "aBook", bookService.findOne(id).get());
    }

    @PostMapping("/editBook")
    public ModelAndView editABook(@Valid @ModelAttribute("aBook") Book b, BindingResult result) {


        if (result.hasErrors()) {
            return new ModelAndView("/editBook");
        }
        bookService.saveBook(b);
        return new ModelAndView("redirect:/book");

    }
//    @ExceptionHandler(NumberFormatException.class)
//    public ModelAndView handleNumberFormatException() {
//        return new ModelAndView("/error", "error", "Number Format Exception");
//    }
//
//    @ExceptionHandler(ArithmeticException.class)
//    public ModelAndView handleArithmeticException() {
//        return new ModelAndView("/error", "error", "Arithmetic Exception");
//    }
    
} 
