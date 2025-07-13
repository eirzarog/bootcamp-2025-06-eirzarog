package dev.redbeans.bootcamp202506eirzarog.springapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Global exception handler

@RestControllerAdvice
//@ControllerAdvice
public class BootcampExceptionHandler {

    // This method will handle all exceptions thrown by the controllers
    // and return a custom error message
    // The method can return any type, but if you want to return a JSON response,
    // you can use a custom class like ErrorMessage
    // or simply return a String that will be treated as a response body

//@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
 @ExceptionHandler(Exception.class)
// public ErrorMessage handleException(Exception e) {

     public ResponseEntity<ErrorMessage> handleException(Exception e) {

     HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 200, 201, 202, 204, 400, 401, 403, 404, 500, 501 not implemented, 502, 503

     if (e instanceof BootcampException) {
         BootcampException bootcampException = (BootcampException) e;
         status = bootcampException.getHttpStatus();
     }

     e.printStackTrace(); // Log the exception to the console or a log file, if you have a logging framework set up. Something was wrong with POST request to /users typo

     ErrorMessage errorMessage = new ErrorMessage(); // breakpoint here
     errorMessage.setErrorMessage("Something went wrong: " + e.getMessage());
     errorMessage.setErrorCode(status.value());

//     return errorMessage;
     return ResponseEntity.status(status).body(errorMessage);
//     when the method returns a String, it will be treated as a response body
//     return "{\"errorMessage\": \"" + e.getMessage() + "\", \"errorCode\": 500}";
    }

}
