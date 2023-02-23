
package L7.source.sd4.repository;


import java.util.List;

import L7.source.sd4.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findBookByAuthorID(Long id);

}
