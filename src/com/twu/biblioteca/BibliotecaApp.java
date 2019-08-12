package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    private String[] listOfBooks = {
            "Book 1 | Author A | 2000",
            "Book 2 | Author B | 2001",
            "Book 3 | Author C | 2002"
    };
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        app.start();
    }

    public void start() {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        System.out.println("Choose an option by entering the associated number and pressing ENTER");
        System.out.println("1. List of books");

        while(scanner.hasNext()) {
            String input = scanner.next();
            if (input.equals("1")) {
                for (String book : listOfBooks) {
                    System.out.println(book);
                }
                break;
            } else {
                System.out.println("Please select a valid option!");
                System.out.println("Choose an option by entering the associated number and pressing ENTER");
                System.out.println("1. List of books");
            }
        }
    }
}