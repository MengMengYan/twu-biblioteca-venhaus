package com.twu.biblioteca;

public class BookNotLoanedByCustomerException extends RuntimeException {
    BookNotLoanedByCustomerException(String message) {
        super(message);
    }
}
