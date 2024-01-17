
package com.main.controllers;
import com.github.javafaker.Faker;
import com.main.service.AuthorService;
import com.main.model.Author;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"/author", "/author/", "/authors","/authors/"})
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    @RequestMapping("")
    public ModelAndView displayAllBooks() {
        return new ModelAndView("/viewAll", "allAuthors", authorService.findAll());
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editAnAuthor(@PathVariable("id") long id) {
        if (authorService.findOne(id).isEmpty())
            return new ModelAndView("/error", "error", "Author not found");
        else {
            Author updateAuthor = authorService.findOne(id).get();

            Faker faker = new Faker();
            updateAuthor.setFirstName(faker.name().firstName());
            updateAuthor.setLastName(faker.name().lastName());
            updateAuthor.setYearBorn(faker.number().numberBetween(1930, 2024));

            authorService.updateBook(updateAuthor);
            return new ModelAndView("redirect:/author");

        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteBook(@PathVariable("id") long id) {
        if (authorService.findOne(id).isEmpty()) {
            return new ModelAndView("/error", "error", "Author not found");
        }
        else {
            authorService.deleteByID(id);
            return new ModelAndView("redirect:/author");
        }
    }

    @RequestMapping("/add")
    public ModelAndView addAnAuthor() {
        Faker faker = new Faker();
        Author newAuthor = new Author();
        newAuthor.setFirstName(faker.name().firstName());
        newAuthor.setLastName(faker.name().lastName());
        newAuthor.setYearBorn(faker.number().numberBetween(1930, 2024));
        authorService.saveAuthor(newAuthor);
        return new ModelAndView("redirect:/author");
    }
}

