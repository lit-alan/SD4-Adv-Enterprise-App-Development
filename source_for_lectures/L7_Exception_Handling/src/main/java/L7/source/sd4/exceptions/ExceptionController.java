/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package L7.source.sd4.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice
public class ExceptionController  {

//     //@ExceptionHandler(value= {BookNotFoundException.class, IllegalStateException.class})
//     @ExceptionHandler(value= BookNotFoundException.class)
//       public ModelAndView handleException(HttpServletRequest req, Exception ex, Object o) {
//        String message = "Request " + req.getRequestURL() + " threw the following exception " + ex;
//        System.out.println(ex); //better to log the exception properly
//        return new ModelAndView("/error", "message", message);
//    }

     //@ExceptionHandler(value= {BookNotFoundException.class, IllegalStateException.class})
//     @ExceptionHandler(value= BookNotFoundException.class)
//       public ResponseEntity<String> handleException(RuntimeException ex, WebRequest req) {
//        String bodyOfResponse = "A " + ex + " error has occured";
//         HttpHeaders headers = new HttpHeaders();
//
//        return new ResponseEntity(bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
//    }
}




