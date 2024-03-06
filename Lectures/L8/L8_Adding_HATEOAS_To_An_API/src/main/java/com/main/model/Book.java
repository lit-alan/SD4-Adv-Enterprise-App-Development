package com.main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookId;

    @NotBlank(message = "{book.titleNotEmpty}")
    private String title;

    @NotBlank(message="{book.publisherNotEmpty}")
    @Size(min = 5, message="{book.publisherSize}")
    private String publisher;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publicationDate;

    @NotNull(message="{book.priceNull}")
    @DecimalMin("1.0")
    private Double price;

    @NotBlank(message="{book.isbnNull}")
    private String isbn;

    private long authorID;

}

