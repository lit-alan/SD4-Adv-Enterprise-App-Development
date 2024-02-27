
package com.main.controllers;
import com.main.service.AuthorService;
import com.main.model.Author;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class AuthorControllerV1 {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/authors")
    public List<Author> getAll() {
        return authorService.findAll();
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> getOne(@PathVariable long id) {
        Optional<Author> o = authorService.findOne(id);

        if (!o.isPresent())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        else
            return ResponseEntity.ok(o.get());
    }

    @GetMapping("/authors/count")
    public long getCount() {
        return authorService.count();
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        authorService.deleteByID(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/authors")
    public ResponseEntity add(@RequestBody Author a) {
        authorService.saveAuthor(a);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/authors")
    public ResponseEntity edit(@RequestBody Author a) {
        authorService.saveAuthor(a);
        return new ResponseEntity(HttpStatus.OK);
    }
}


//    @GetMapping("/authors/findby/firstname/prefix/{prefix}")
//    public List<Author> getAllByPrefix(@PathVariable String prefix) {
//        return authorService.findByFirstNameNameStartingWith(prefix);
//    }
//
//    @GetMapping("/authors/findby/firstname/infix/{infix}")
//    public List<Author> getAllByInfix(@PathVariable String infix) {
//        return authorService.findByfirstNameContaining(infix);
//    }
//
//    @GetMapping("/authors/findby/lastname/suffix/{suffix}")
//    public List<Author> findBylastNameEndingWith(@PathVariable String suffix) {
//        return authorService.findByFirstNameNameEndingWith(suffix);
//    }
//
//    @GetMapping("/authors/bornbetween/{start}/{end}")
//    public List<Author> getBornBetween(@PathVariable Integer start, @PathVariable Integer end) {
//        return authorService.findAuthorByYearBornBetweenOrderByFirstName(start, end);
//    }
//
//    @GetMapping("/authors/bornBefore/{yearBorn}")
//    public List<Author> findByyearBornLessThanEqual(@PathVariable int yearBorn) {
//        return authorService.findByyearBornLessThanEqual(yearBorn);
//    }
//
//    @GetMapping("/authors/avg/yearborn/")
//    public int getAvgYearBorn() {
//        return authorService.getAvgYearBorn();
//    }
//









//    @GetMapping("/authors/avg/yearborn/")
//    public int getAvgYearBorn() {
//        return authorService.getAvgYearBorn();
//    }
//
//
//    @GetMapping("/authors/bornbetween/{start}/{end}")
//    public List<Author> getBornBetween(@PathVariable Integer start, @PathVariable Integer end) {
//        return authorService.findAuthorByYearBornBetweenOrderByFirstName(start, end);
//    }
//
//    @GetMapping("/authors/bornBefore/{yearBorn}")
//    public List<Author> findByyearBornLessThanEqual(@PathVariable int yearBorn) {
//        return authorService.findByyearBornLessThanEqual(yearBorn);
//    }
//
//    @GetMapping("/authors/findby/firstname/prefix/{prefix}")
//    public List<Author> getAllByPrefix(@PathVariable String prefix) {
//        return authorService.findByFirstNameNameStartingWith(prefix);
//    }
//
//    @GetMapping("/authors/findby/firstname/infix/{infix}")
//    public List<Author> getAllByInfix(@PathVariable String infix) {
//        return authorService.findByfirstNameContaining(infix);
//    }
//
//    @GetMapping("/authors/findby/lastname/suffix/{suffix}")
//    public List<Author> findBylastNameEndingWith(@PathVariable String suffix) {
//        return authorService.findBylastNameEndingWith(suffix);
//    }