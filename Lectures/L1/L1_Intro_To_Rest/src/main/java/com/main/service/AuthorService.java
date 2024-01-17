package com.main.service;

import com.main.model.Author;
import com.main.repository.AuthorRepository;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService  {

    @Autowired
    private AuthorRepository authorRepo;

    public Optional<Author> findOne(Long id) {
        return authorRepo.findById(id);
    }

    public List<Author> findAll() {
        return (List<Author>) authorRepo.findAll();
    }

    public long count() {
        return authorRepo.count();
    }

    public void deleteByID(long authorID) {
        authorRepo.deleteById(authorID);
    }

    public void saveAuthor(Author a) {
        authorRepo.save(a);
    }

    public Author updateBook(Author updatedAuthor) {
        Optional<Author> existingAuthor = authorRepo.findById(updatedAuthor.getAuthorID());

        if (existingAuthor.isPresent()) {
            Author author = existingAuthor.get();

            // Set new values for the author attributes you need to update
            author.setFirstName(updatedAuthor.getFirstName());
            author.setLastName(updatedAuthor.getLastName());
            author.setYearBorn(updatedAuthor.getYearBorn());

            // Save the author back to the database
            authorRepo.save(author);

            return author;
        } else {
            // Handle the case where the author is not found
            throw new EntityNotFoundException("Author not found with id " + updatedAuthor.getAuthorID());
        }
    }

    public List<Author> findByFirstNameNameStartingWith(String prefix) {
        return (List<Author>) authorRepo.findAllByFirstNameStartingWith(prefix);
    }

    public List<Author> findByFirstNameNameEndingWith(String suffix) {
        return (List<Author>) authorRepo.findAllByFirstNameEndingWith(suffix);
    }

    public List<Author> findByfirstNameContaining(String infix) {
        return (List<Author>) authorRepo.findAllByFirstNameContaining(infix);
    }

    public List<Author> findAuthorByYearBornBetweenOrderByFirstName(Integer start, Integer end) {
        return (List<Author>) authorRepo.findAuthorByYearBornBetweenOrderByFirstName(start, end);
    }

    public List<Author> findByyearBornLessThanEqual(int year) {
        return (List<Author>) authorRepo.findByyearBornLessThanEqual(year);
    }

          public Integer getAvgYearBorn() {
        return authorRepo.getAverageYearBorn();
    }
}//end class



//    public Integer getAvgYearBorn() {
//        return authorRepo.getAverageYearBorn();
//    }
//
//    public List<Author> findAuthorByYearBornBetweenOrderByFirstName(Integer start, Integer end) {
//        return (List<Author>) authorRepo.findAuthorByYearBornBetweenOrderByFirstName(start, end);
//    }
//
//    public List<Author> findByyearBornLessThanEqual(int year) {
//        return (List<Author>) authorRepo.findByyearBornLessThanEqual(year);
//    }
//
//    public List<Author> findByFirstNameNameStartingWith(String prefix) {
//        return (List<Author>) authorRepo.findByfirstNameStartingWith(prefix);
//    }
//
//    public List<Author> findByfirstNameContaining(String infix) {
//        return (List<Author>) authorRepo.findByfirstNameContaining(infix);
//    }
//
//    public List<Author> findBylastNameEndingWith(String suffix) {
//        return (List<Author>) authorRepo.findBylastNameEndingWith(suffix);
//    }
