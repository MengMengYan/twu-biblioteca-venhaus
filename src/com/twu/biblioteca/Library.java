package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.BookNonexistentException;
import com.twu.biblioteca.exceptions.BookUnavailableException;

import java.util.ArrayList;
import java.util.List;

import static com.twu.biblioteca.Text.FAILURE_CHECKOUT_LOANED;
import static com.twu.biblioteca.Text.FAILURE_CHECKOUT_NONEXISTENT;

/*
TODO what is the reason behind making these methods static? I was told the code is supposed to be object oriented.
 */
public class Library {

    private static List<Book> bookList = new ArrayList<>();
    
    public static void addBook(Book book) {
        bookList.add(book);
    }

    public static List<Book> getBookList() {
        return new ArrayList<>(bookList);
    }

    public static Book getBook(int bookIdx) throws BookNonexistentException {
        Book book;
        try {
            book = bookList.get(bookIdx);
        } catch (IndexOutOfBoundsException e) {
            throw new BookNonexistentException(FAILURE_CHECKOUT_NONEXISTENT);
        }
        return book;
    }

    public static Book loanBook(int bookIdx) throws BookUnavailableException, BookNonexistentException {

        Book book = getBook(bookIdx);

        if (!book.isAvailable()) {
            throw new BookUnavailableException(FAILURE_CHECKOUT_LOANED);
        } else {
            book.setAvailable(false);
            return book;
        }
    }

    public static void reset() {
        bookList = new ArrayList<>();
    }
}
