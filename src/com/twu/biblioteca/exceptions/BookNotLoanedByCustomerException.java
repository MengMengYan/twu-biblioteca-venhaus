package com.twu.biblioteca.exceptions;

public class BookNotLoanedByCustomerException extends Exception {
    public BookNotLoanedByCustomerException(String message) {
        super(message);
    }
}
