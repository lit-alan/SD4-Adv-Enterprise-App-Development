
package sd4.l6_exception_handling.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sd4.l6_exception_handling.model.Book;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findBookByAuthorID(Long id);

}
