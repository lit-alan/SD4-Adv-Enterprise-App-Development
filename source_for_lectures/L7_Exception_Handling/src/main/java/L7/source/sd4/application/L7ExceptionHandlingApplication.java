package L7.source.sd4.application;

import L7.source.sd4.model.Author;
import L7.source.sd4.model.Book;
import L7.source.sd4.repository.AuthorRepository;
import L7.source.sd4.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;

@SpringBootApplication
@ComponentScan({"L7.source.sd4.service", "L7.source.sd4.controllers", "L7.source.sd4.exceptions"})
@EntityScan("L7.source.sd4.model")
@EnableJpaRepositories("L7.source.sd4.repository")
public class L7ExceptionHandlingApplication implements CommandLineRunner {


    @Autowired
    private AuthorRepository authorRepo;

    @Autowired
    private BookRepository bookRepo;

    public static void main(String[] args)  {

        SpringApplication.run(L7ExceptionHandlingApplication.class, args);
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
