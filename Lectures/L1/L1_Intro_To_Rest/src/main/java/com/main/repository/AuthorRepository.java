
package com.main.repository;


import com.main.model.Author;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    List<Author> findAllByFirstNameStartingWith(String prefix);

    List<Author> findAllByFirstNameEndingWith(String suffix);

    List<Author> findAllByFirstNameContaining(String infix);

    List<Author> findAuthorByYearBornBetweenOrderByFirstName(Integer start, Integer end);

    List<Author> findByyearBornLessThanEqual(Integer yearBorn);

    @Query("SELECT AVG(a.yearBorn) from Author a")
    int getAverageYearBorn();
}

