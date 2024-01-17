package com.main.application;

import com.main.model.Author;
import com.main.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.main.service", "com.main.controllers"})
@EntityScan("com.main.model")
@EnableJpaRepositories("com.main.repository")
public class L1IntroToMVCApplication implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepo;

    public static void main(String[] args) {
        SpringApplication.run(L1IntroToMVCApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        authorRepo.save(new Author(1, "Seamus", "Watson", 1949));
        authorRepo.save(new Author(2, "Sharon", "Guinane", 1939));
        authorRepo.save(new Author(3, "Gerry", "Byrne", 1965));
        authorRepo.save(new Author(4, "Brendan", "Doyle", 1972));
    }
}

