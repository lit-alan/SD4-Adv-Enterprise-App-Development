package sd4.l6_exception_handling.model;

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
    
    @NotBlank(message = "Title cannot be empty")
    private String title;
   
    @NotBlank(message="Publisher cannot be empty")
    @Size(min = 5, message="Publisher must be at least 5 characters")
    private String publisher;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
   // @Past
   // @Future
    private Date publicationDate;
    
    @NotNull(message="Price cannot be empty")
    @DecimalMin("1.0")
    private Double price;
    
    @NotBlank(message="ISBN cannot be null")    
    private String isbn;
    
    private long authorID;   
    
}

