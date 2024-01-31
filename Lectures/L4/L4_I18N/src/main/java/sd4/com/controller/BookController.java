/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd4.com.controller;


import jakarta.validation.Valid;
import sd4.com.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sd4.com.service.BookService;

/**
 *
 * @author Alan.Ryan
 */
@Controller
@RequestMapping(value = {"/book", "/book/"})
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("")
    public ModelAndView displayAllBooks() {
        System.out.println(bookService.findAll().size());
        return new ModelAndView("/viewAll", "books", bookService.findAll());
    }
    //////////////////
    @GetMapping("/add")
    public ModelAndView showAddBookForm() {
        return new ModelAndView("/addBook", "aBook", new Book());

    }


    @PostMapping("/addBook")
    public ModelAndView addABook(@jakarta.validation.Valid @ModelAttribute("aBook") Book b, BindingResult result) {
        if (result.hasErrors())
            return new ModelAndView("/addBook");

        bookService.saveBook(b); // Save new book
        return new ModelAndView("redirect:/book/");

    }

    //////////////////

    @GetMapping("/edit/{id}")
    public ModelAndView showEditBookForm(@PathVariable("id") long id) {
        if (bookService.findOne(id).isEmpty())
            return new ModelAndView("/error", "error", "Book not found");
        else
            return new ModelAndView("/editBook", "aBook", bookService.findOne(id).get());
    }

    @PostMapping("/editBook")
    public ModelAndView updateABook(@jakarta.validation.Valid @ModelAttribute("aBook") Book b, BindingResult result) {


        if (result.hasErrors())
            return new ModelAndView("/editBook");

        bookService.updateBook(b);
        return new ModelAndView("redirect:/book");
    }

    /* The addABook and updateABook methods above can be combined into one method.
       The method below first of all checks if there are any validation errors. If there are, determine the appropriate
       view to return (addBook or editBook) based on whether the book's ID is null.
       If there are no errors, check if the book's ID is null. If it is, you treat it as a new book and call saveBook.
       If the ID is not null, treat it as an existing book and call updateBook.
       After either saving or updating the book, redirect to the /book
     */
    @PostMapping("/saveBook")
    public ModelAndView saveOrUpdateBook(@Valid @ModelAttribute("aBook") Book b, BindingResult result) {
        if (result.hasErrors()) {
            // Determine whether it's an add or update operation based on book ID
            String viewName = (b.getBookId() == null) ? "/addBook" : "/editBook";
            return new ModelAndView(viewName);
        }

        if (b.getBookId() == null) {
            bookService.saveBook(b); // Save new book
        } else {
            bookService.updateBook(b); // Update existing book
        }

        return new ModelAndView("redirect:/book");
    }

    //////////////////
    @GetMapping("/delete/{id}")
    public ModelAndView deleteBook(@PathVariable("id") long id) {
        if (bookService.findOne(id).isEmpty()) {
            return new ModelAndView("/error", "error", "Book not found");
        }
        else {
            bookService.deleteByID(id);
            return new ModelAndView("redirect:/book/");
        }
    }
}
