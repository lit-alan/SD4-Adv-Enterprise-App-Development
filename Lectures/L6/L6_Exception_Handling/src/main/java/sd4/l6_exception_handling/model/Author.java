
package sd4.l6_exception_handling.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Author  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorID;
    private String firstName;
    private String lastName;
    private int yearBorn;
}

