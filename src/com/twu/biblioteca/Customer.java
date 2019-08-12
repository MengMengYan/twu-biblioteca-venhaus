package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.BookNotLoanedByCustomerException;

import java.util.ArrayList;
import java.util.List;

import static com.twu.biblioteca.Text.FAILURE_RETURN_BOOK_NOT_LOANED_BY_CUSTOMER;

public class Customer {

    private List<Book> bookList = new ArrayList<>();

    public List<Book> getBookList() {
        return new ArrayList<>(bookList);
    }

    public void checkOutBook(int bookIdx) {
        Book book = Library.loanBook(bookIdx);
        bookList.add(book);
    }

    public void returnBook(int bookIdx) {
        Book book;
        try {
            book = bookList.get(bookIdx);
            book.setAvailable(true);
            bookList.remove(bookIdx);
        } catch (IndexOutOfBoundsException e) {
            throw new BookNotLoanedByCustomerException(FAILURE_RETURN_BOOK_NOT_LOANED_BY_CUSTOMER);
        }
    }
}
