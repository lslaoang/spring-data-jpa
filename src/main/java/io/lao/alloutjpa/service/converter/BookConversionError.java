package io.lao.alloutjpa.service.converter;

public class BookConversionError extends RuntimeException{
    public BookConversionError(String message) {
        super(message);
    }
}
