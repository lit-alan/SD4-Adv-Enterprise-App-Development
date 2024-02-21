package sd4.l6_exception_handling.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd4.l6_exception_handling.model.Author;
import sd4.l6_exception_handling.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;


@Service
public class AuthorService {

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
    
}//end class