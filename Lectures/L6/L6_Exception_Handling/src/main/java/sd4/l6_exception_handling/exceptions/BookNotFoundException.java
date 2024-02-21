package sd4.l6_exception_handling.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Book Not Found")
public class BookNotFoundException extends RuntimeException {
    
}
