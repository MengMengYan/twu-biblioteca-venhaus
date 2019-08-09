package com.twu.biblioteca;

public class BibliotecaApp {

    private String[] listOfBooks = {"Book 1", "Book 2", "Book 3"};

    public static void main(String[] args) {
    }

    public void start() {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        for (String book : listOfBooks) {
            System.out.println(book);
        }
    }
}
