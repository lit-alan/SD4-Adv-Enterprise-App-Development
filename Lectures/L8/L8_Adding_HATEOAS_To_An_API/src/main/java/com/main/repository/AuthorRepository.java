
package com.main.repository;


import com.main.model.Author;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    /*  This method will find all Author entities that have a lastName field matching the provided lastName string.
     *  The results will be returned as a Page, which means they will be paginated according to the Pageable parameter.
     *  Pagination allows you to request a subset of results within a certain range, specified by the page number and
     *  page size in the Pageable object.
     */
    Page<Author> findAllByLastName(String lastName, Pageable pageable);


    /*  This method will find all Author entities whose yearBorn field matches the provided yearBorn integer value.
     *  The results are also paginated as per the provided Pageable object.
     */
    Page<Author> findAllByYearBorn(int yearBorn, Pageable pageable);

    /*  This method will find all Author entities where the firstName field contains the provided firstName string and the lastName
     * field contains the provided lastName string. The 'Containing' keyword implies a LIKE operation in SQL, and the IgnoreCase
     * keyword implies that the case of the actual content of the fields should be ignored during the comparison
     * (making the search case-insensitive). The results will be paginated based on the Pageable parameter.
     */
    Page<Author> findAllByFirstNameContainingAndLastNameContainingAllIgnoreCase(
            String firstName, String lastName, Pageable pageable);


    List<Author> findAllByFirstNameStartingWith(String prefix);

    List<Author> findAllByFirstNameEndingWith(String suffix);

    List<Author> findAllByFirstNameContaining(String infix);

    List<Author> findAuthorByYearBornBetweenOrderByFirstName(Integer start, Integer end);

    List<Author> findByyearBornLessThanEqual(Integer yearBorn);

    Page<Author> findByYearBornBetween(Integer startYear, Integer endYear, Pageable pageable);

    @Query("SELECT AVG(a.yearBorn) from Author a")
    int getAverageYearBorn();

}