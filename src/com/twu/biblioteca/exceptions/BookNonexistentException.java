package com.twu.biblioteca.exceptions;

public class BookNonexistentException extends RuntimeException {
    public BookNonexistentException(String message) {
        super(message);
    }
}
