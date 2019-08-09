package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    private String[] listOfBooks = {
            "Book 1 | Author A | 2000",
            "Book 2 | Author B | 2001",
            "Book 3 | Author C | 2002"
    };

    public void start() {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        System.out.println("Choose an option by entering the associated number and pressing ENTER");
        System.out.println("1. List of books");
        for (String book : listOfBooks) {
            System.out.println(book);
        }
    }
}
