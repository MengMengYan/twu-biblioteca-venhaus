package com.twu.biblioteca;

public class AppSetup {

    static void setupLibrary() {
        Book book0 = new Book();
        book0.setAuthor("Robert Martin");
        book0.setTitle("Clean Code");
        book0.setYear(2006);
        Library.addBook(book0);

        Book book1 = new Book();
        book1.setAuthor("J.K. Rowling");
        book1.setTitle("Harry Potter");
        book1.setYear(1998);
        Library.addBook(book1);
    }

}
