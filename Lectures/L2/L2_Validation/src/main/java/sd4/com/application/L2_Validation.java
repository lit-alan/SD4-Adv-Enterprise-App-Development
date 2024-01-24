package sd4.com.application;

import com.github.javafaker.Faker;
import sd4.com.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import sd4.com.service.BookService;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SpringBootApplication
@ComponentScan({"sd4.com.service", "sd4.com.controllers"})
@EntityScan("sd4.com.model")
@EnableJpaRepositories("sd4.com.repository")
public class L2_Validation implements CommandLineRunner {

    @Autowired
    public BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(L2_Validation.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Date startDate = new GregorianCalendar(2007, Calendar.JANUARY, 1).getTime();
        Date today = new Date();
        Faker faker = new Faker();


        bookService.saveBook(new Book(Long.valueOf(10), "Hitting The High Notes", "LIT Publishing",faker.date().between(startDate, today) , 1.25, faker.code().isbn13(true) ,1));
        bookService.saveBook(new Book(Long.valueOf(11), "Life, Love and LIT", "LIT Publishing", faker.date().between(startDate, today) , 1.25, faker.code().isbn13(true) ,4));
        bookService.saveBook(new Book(Long.valueOf(12), "How To Survive In A World Gone Mad", "LIT Publishing", faker.date().between(startDate, today) , 1.25, faker.code().isbn13(true) ,3));
        bookService.saveBook(new Book(Long.valueOf(13), "The Running Man", "LIT Publishing",faker.date().between(startDate, today) , 1.25, faker.code().isbn13(true) ,1));
        bookService.saveBook(new Book(Long.valueOf(14), "Snamhai Sasta", "Dark Work Publishing", faker.date().between(startDate, today) , 1.25, faker.code().isbn13(true) ,2));
        bookService.saveBook(new Book(Long.valueOf(15), "No More Monkey Business", "IB Publishing", faker.date().between(startDate, today)  , 1.25, faker.code().isbn13(true) ,1));

    }

}
