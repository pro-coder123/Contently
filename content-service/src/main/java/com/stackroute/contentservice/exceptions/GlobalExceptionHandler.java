package com.stackroute.contentservice.exceptions;


import com.stackroute.contentservice.exceptions.ContentAlreadyExistsExceptions;
import com.stackroute.contentservice.exceptions.ContentDoesNotExistException;
import com.stackroute.contentservice.exceptions.NullValueFieldException;
import com.stackroute.contentservice.exceptions.InternalServerErrorException;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ContentAlreadyExistsExceptions.class)
    public ResponseEntity<?> handleAlreadyExistsException(HttpServletRequest request, Exception ex){
        return new ResponseEntity<String>("Content Already Exists", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NullValueFieldException.class)
    public ResponseEntity<?> handleNullValueException(HttpServletRequest request, Exception ex){
        return new ResponseEntity<String>("Enter Proper Value", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ContentDoesNotExistException.class)
    public ResponseEntity<?> handleUserDoesNotException(HttpServletRequest request, Exception ex){
        return new ResponseEntity<String>("Content Does Not Exist", HttpStatus.CONFLICT);
    }
    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<?> handleInternalServerErrorException(HttpServletRequest request, Exception ex){
        return new ResponseEntity<String>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
