
package com.sd4.repository;

import com.sd4.model.Author;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    public List<Author> getAuthorsByFirstNameStartingWith(String prefix);
    
 
}
