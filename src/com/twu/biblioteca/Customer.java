package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private List<Book> bookList = new ArrayList<>();

    public List<Book> getBookList() {
        return new ArrayList<>(bookList);
    }

    public void checkOutBook(int bookIdx) {
        Book book = Library.loanBook(bookIdx);
        bookList.add(book);
    }
}
