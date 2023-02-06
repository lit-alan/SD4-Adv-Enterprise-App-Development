package sd4.service;

import java.util.List;
import java.util.Optional;

import sd4.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd4.repository.BookRepository;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepo;

    public Optional<Book> findOne(Long id) {
        return bookRepo.findById(id);
    }

    public List<Book> findAll() {
        return (List<Book>) bookRepo.findAll();
    }
    
//  //   public List<Book> findBooksByAuthor(Long authorID) {
//        return bookRepo.findBookByAuthorID(authorID);
//    }

    public long count() {
        return bookRepo.count();
    }

    public void deleteByID(long authorID) {
        bookRepo.deleteById(authorID);
    }

    public void saveBook(Book a) {
        bookRepo.save(a);
    }  
    
    
}//end class

