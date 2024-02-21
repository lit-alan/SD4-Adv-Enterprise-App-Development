package sd4.l6_exception_handling.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sd4.l6_exception_handling.model.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    
 
}
