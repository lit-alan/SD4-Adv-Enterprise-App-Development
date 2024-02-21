package sd4.l6_exception_handling.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import sd4.l6_exception_handling.model.Author;
import sd4.l6_exception_handling.model.Book;
import sd4.l6_exception_handling.repository.AuthorRepository;
import sd4.l6_exception_handling.repository.BookRepository;

import java.util.Date;

@SpringBootApplication
@ComponentScan({"sd4.l6_exception_handling.service", "sd4.l6_exception_handling.controllers", "sd4.l6_exception_handling.exceptions"})
@EntityScan("sd4.l6_exception_handling.model")
@EnableJpaRepositories("sd4.l6_exception_handling.repository")
public class L6ExceptionHandlingApplication implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepo;

    @Autowired
    private BookRepository bookRepo;

    public static void main(String[] args) {
        SpringApplication.run(L6ExceptionHandlingApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        authorRepo.save(new Author(1, "Brendan", "Watson", 1949));
        authorRepo.save(new Author(2, "Gerry", "Guinnane", 1939));
        authorRepo.save(new Author(3, "Sharon", "Byrne", 1965));
        authorRepo.save(new Author(4, "Seamus", "Doyle", 1972));

        Date now = new Date();
        bookRepo.save(new Book(10, "Hitting The High Notes", "LIT Publishing", new Date(now.getTime() - (1000 * 60 * 60 * 24 * 5)) , 1.25, "BR78569524" ,1));
        bookRepo.save(new Book(11, "Life, Love and LIT", "LIT Publishing",  new Date(now.getTime() - (1000 * 60 * 60 * 24 * 10)), 16.25, "PP98765431",4));
        bookRepo.save(new Book(12, "How To Survive In A World Gone Mad", "LIT Publishing",  new Date(now.getTime() - (1000 * 60 * 60 * 24 * 15)), 19.99, "XDW34234123",3));
        bookRepo.save(new Book(13, "The Running Man", "LIT Publishing",  new Date(now.getTime() - (1000 * 60 * 60 * 24 * 15)), 9.99, "PD404234123",1));
        bookRepo.save(new Book(14, "Snamhai Sasta", "Dark Work Publishing",  new Date(now.getTime() - (1000 * 60 * 60 * 24 * 25)), 16.99, "MJ7y234123",2));
        bookRepo.save(new Book(15, "No More Monkey Business", "IB Publishing", new Date(now.getTime() - (1000 * 60 * 60 * 24 * 25)), 16.99, "MJ7y234123",1));


    }
}
