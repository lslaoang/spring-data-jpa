package io.lao.alloutjpa.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookExceptionHandler {

    final static String ALREADY_EXIST = "Book already exist. ";
    final static String BOOK_NOT_FOUND = "Cannot find book in the record. ";

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<?> handleBookAlreadyExistsException(BookAlreadyExistsException exception) {
        return new ResponseEntity<>(ALREADY_EXIST + exception.message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> handleBookNotFoundException(BookNotFoundException exception) {
        return new ResponseEntity<>(BOOK_NOT_FOUND + exception.message, HttpStatus.NOT_FOUND);
    }
}
