/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd4.controller;


import javax.validation.Valid;

import sd4.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sd4.service.BookService;

/**
 *
 * @author Alan.Ryan
 */
@Controller()
@RequestMapping("/book/")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("")
    public ModelAndView displayAllBooks() {
        return new ModelAndView("/viewAll", "books", bookService.findAll());
    }
    
    @GetMapping("/add")
    public ModelAndView displayAddForm() {
        return new ModelAndView("/addBook", "aBook", new Book());

    }

    @PostMapping("/addBook")
    public ModelAndView addABook(@Valid @ModelAttribute("aBook") Book b, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("/addBook");
        }
        bookService.saveBook(b);
        return new ModelAndView("redirect:/book");

    }
    
    @PostMapping("/editBook")
    public ModelAndView editABook(@Valid @ModelAttribute("aBook") Book b, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("/editBook");
        }
        bookService.saveBook(b);
        return new ModelAndView("redirect:/book");

    }

    @GetMapping("/edit/{id}")
    public ModelAndView displayEditForm(@PathVariable("id") long id) {
         if (bookService.findOne(id).isEmpty())
            return new ModelAndView("/error", "error", "Book not found");
        else 
            return new ModelAndView("/editBook", "aBook", bookService.findOne(id).get());
    }
}
