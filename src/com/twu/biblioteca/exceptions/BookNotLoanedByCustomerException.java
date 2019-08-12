package com.twu.biblioteca.exceptions;

public class BookNotLoanedByCustomerException extends RuntimeException {
    public BookNotLoanedByCustomerException(String message) {
        super(message);
    }
}
