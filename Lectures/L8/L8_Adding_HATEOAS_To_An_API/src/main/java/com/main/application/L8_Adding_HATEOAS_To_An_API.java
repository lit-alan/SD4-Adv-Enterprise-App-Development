package com.main.application;

import com.main.model.Author;
import com.main.model.Book;
import com.main.repository.AuthorRepository;
import com.main.service.AuthorService;
import com.main.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;

@SpringBootApplication
@ComponentScan({"com.main.service", "com.main.controllers"})
@EntityScan("com.main.model")
@EnableJpaRepositories("com.main.repository")
public class L8_Adding_HATEOAS_To_An_API implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepo;

    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(L8_Adding_HATEOAS_To_An_API.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        authorRepo.save(new Author(1, "Alex", "Watson", 1949));
        authorRepo.save(new Author(2, "Gerry", "Guinane", 1939));
        authorRepo.save(new Author(3, "Sharon", "Byrne", 1965));
        authorRepo.save(new Author(4, "Alan", "Doyle", 1972));
        authorRepo.save(new Author(5, "Barry", "Coyle", 1972));


        Date now = new Date();
        bookService.saveBook(new Book(10, "Hitting The High Notes", "LIT Publishing", new Date(now.getTime() - (1000 * 60 * 60 * 24 * 5)) , 1.25, "BR78569524" ,1));
        bookService.saveBook(new Book(11, "Life, Love and LIT", "LIT Publishing",  new Date(now.getTime() - (1000 * 60 * 60 * 24 * 10)), 16.25, "PP98765431",4));
        bookService.saveBook(new Book(12, "How To Survive In A World Gone Mad", "LIT Publishing",  new Date(now.getTime() - (1000 * 60 * 60 * 24 * 15)), 19.99, "XDW34234123",3));
        bookService.saveBook(new Book(13, "The Running Man", "LIT Publishing",  new Date(now.getTime() - (1000 * 60 * 60 * 24 * 15)), 9.99, "PD404234123",1));
        bookService.saveBook(new Book(14, "Snamhai Sasta", "Dark Work Publishing",  new Date(now.getTime() - (1000 * 60 * 60 * 24 * 25)), 16.99, "MJ7y234123",2));
        bookService.saveBook(new Book(15, "No More Monkey Business", "IB Publishing", new Date(now.getTime() - (1000 * 60 * 60 * 24 * 25)), 16.99, "MJ7y234123",1));

    }

    @Bean
    public WebMvcConfigurer customConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                configurer.defaultContentType(MediaType.APPLICATION_JSON);
            }
        };
    }

}
