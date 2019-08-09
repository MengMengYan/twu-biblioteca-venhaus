package com.twu.biblioteca;

public class BibliotecaApp {

    private String[] listOfBooks = {
            "Book 1 | Author A | 2000",
            "Book 2 | Author B | 2001",
            "Book 3 | Author C | 2002"
    };

    public static void main(String[] args) {
    }

    public void start() {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        for (String book : listOfBooks) {
            System.out.println(book);
        }
    }
}
