package l4.source.sd4.controllers;


import javax.validation.Valid;

import l4.source.sd4.exceptions.BookNotFoundException;
import l4.source.sd4.model.Book;
import l4.source.sd4.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookMVCController {
   
    @Autowired
    private BookService bookService;


//    @ModelAttribute
//    private void getList(Model model) {
//        model.addAttribute("books", bookService.findAll());
//    }

    @RequestMapping("")
    public ModelAndView getAllBooks() {

        return new ModelAndView("/viewAll", "books", bookService.findAll());
    }


    @GetMapping("/add")
    public ModelAndView displayAddForm() {
        Book b = new Book();
        return new ModelAndView("/addBook", "aBook", b);

    }

    @PostMapping("/addBook")
    public ModelAndView addAnAgent(@Valid @ModelAttribute("aBook") Book b, BindingResult result) {
        if (result.hasErrors())
            return new ModelAndView("/addBook");

        bookService.saveBook(b);
        return new ModelAndView("redirect:/book");
    }
    
    @GetMapping("/title/{id}")
    @ResponseBody
    public String getTitleByID(@PathVariable("id") long id) {
        Optional<Book> o = bookService.findOne(id);
        if (!o.isPresent()) {
            throw new BookNotFoundException();
        } else {
            return o.get().getTitle();
        }
    }

//    @GetMapping("/edit/{id}")
//    public ModelAndView displayEditForm(@PathVariable("id") long id){
//        if (bookService.findOne(id) == null) {
//            throw new BookNotFoundException();
//        }
//
//        else {
//            return new ModelAndView("/editBook", "aBook", bookService.findOne(id));
//        }
//    }
    
} 
