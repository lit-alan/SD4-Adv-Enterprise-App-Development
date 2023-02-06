package sd4.model;

import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Alan.Ryan
 */
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


