package com.twu.biblioteca;

public class BookUnavailableException extends RuntimeException {
    BookUnavailableException(String message) {
        super(message);
    }
}
