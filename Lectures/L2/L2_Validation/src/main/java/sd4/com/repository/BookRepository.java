
package sd4.com.repository;


import sd4.com.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {


    public Optional<Book> findByTitleAndIsbn(String title, String ISBN);
}
