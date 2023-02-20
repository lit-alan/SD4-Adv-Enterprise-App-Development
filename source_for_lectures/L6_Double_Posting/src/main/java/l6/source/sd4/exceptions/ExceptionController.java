/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l6.source.sd4.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
//
//@ControllerAdvice
//public class ExceptionController  {
//    
//    
//     //@ExceptionHandler(value= {BookNotFoundException.class, IllegalStateException.class})
//     @ExceptionHandler(value= BookNotFoundException.class)
//       public ResponseEntity<String> handleException(RuntimeException ex, WebRequest req) {
//        String bodyOfResponse = "A " + ex + " error has occured";
//        return new ResponseEntity(bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
//    }
//}

