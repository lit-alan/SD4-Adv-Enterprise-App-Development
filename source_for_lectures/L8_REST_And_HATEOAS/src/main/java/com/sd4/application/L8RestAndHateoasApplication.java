package com.sd4.application;

import com.sd4.model.Author;
import com.sd4.model.Book;
import com.sd4.repository.AuthorRepository;
import com.sd4.repository.BookRepository;
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
@ComponentScan({"com.sd4.service", "com.sd4.controller", "com.sd4.exceptions"})
@EntityScan("com.sd4.model")
@EnableJpaRepositories("com.sd4.repository")
public class L8RestAndHateoasApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(L8RestAndHateoasApplication.class, args);
    }


    @Autowired
    private AuthorRepository authorRepo;
    @Autowired
    private BookRepository bookRepo;

    @Override
    public void run(String... args) throws Exception {
        authorRepo.save(new Author(1,"Brendan", "Watson", 1949));
        authorRepo.save(new Author(2, "Gerry", "Guinnane", 1939));
        authorRepo.save(new Author(3, "Sharon", "Byrne", 1965));
        authorRepo.save(new Author(4,"Seamus", "Doyle", 1972));

        Date now = new Date();
        bookRepo.save(new Book(10, "Hitting The High Notes", "LIT Publishing", new Date(now.getTime() - (1000 * 60 * 60 * 24 * 5)) , 1.25, "BR78569524" ,1));
        bookRepo.save(new Book(11, "Life, Love and LIT", "LIT Publishing",  new Date(now.getTime() - (1000 * 60 * 60 * 24 * 10)), 16.25, "PP98765431",4));
        bookRepo.save(new Book(12, "How To Survive In A World Gone Mad", "LIT Publishing",  new Date(now.getTime() - (1000 * 60 * 60 * 24 * 15)), 19.99, "XDW34234123",3));
        bookRepo.save(new Book(13, "The Running Man", "LIT Publishing",  new Date(now.getTime() - (1000 * 60 * 60 * 24 * 15)), 9.99, "PD404234123",1));
        bookRepo.save(new Book(14, "Snamhai Sasta", "Dark Work Publishing",  new Date(now.getTime() - (1000 * 60 * 60 * 24 * 25)), 16.99, "MJ7y234123",2));
        bookRepo.save(new Book(15, "No More Monkey Business", "IB Publishing", new Date(now.getTime() - (1000 * 60 * 60 * 24 * 25)), 16.99, "MJ7y234123",1));



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


