package io.lao.alloutjpa.controller.exception;

public class BookAlreadyExistsException extends RuntimeException{
    String message;

    public BookAlreadyExistsException(String message) {
        this.message = message;
    }

    public BookAlreadyExistsException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }

    public BookAlreadyExistsException(){

    }
}
