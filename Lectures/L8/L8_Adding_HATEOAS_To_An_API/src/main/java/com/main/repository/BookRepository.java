
package com.main.repository;

import com.main.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    

    public List<Book> getAllByAuthorID(Long id);
}
