package com.rawat.todo.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class globalExceptionHandler {

    // We will create Handler methods for Specific Exception;

    Logger logger = LoggerFactory.getLogger(globalExceptionHandler.class);

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> globalNullPointerException(NullPointerException ex){
        System.out.println("Null Pointer Exception: "+ex.getMessage());
        logger.info("Null Pointer Exception from GlobalExceptionHandler: "+ex.getMessage());
        return new ResponseEntity<>("Null Pointer Exception Ouccred from globalExceptionHandler: "+ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }


    // Handling Resource Not Found Exception
    @ExceptionHandler(resourceNotFoundException.class)
    public ResponseEntity<exceptionResponse> handlerResourceNotFoundException(resourceNotFoundException r){
               logger.info(r.getMessage());
               exceptionResponse response = new exceptionResponse();
               response.setMessage(r.getMessage());
               response.setStatus(HttpStatus.NOT_FOUND);
               response.setSuccess(false);



        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

}
