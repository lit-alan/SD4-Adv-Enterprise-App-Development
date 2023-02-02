package l4.source.sd4.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

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

