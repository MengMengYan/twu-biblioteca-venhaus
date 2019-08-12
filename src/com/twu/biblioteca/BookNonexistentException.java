package com.twu.biblioteca;

public class BookNonexistentException extends RuntimeException {
    public BookNonexistentException(String message) {
        super(message);
    }
}
