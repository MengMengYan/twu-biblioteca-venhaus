package com.twu.biblioteca;

public class Text {
    public static final String OPT_LIST_OF_BOOKS = "1";
    public static final String OPT_CHECKOUT_BOOK = "2";
    public static final String OPT_QUIT = "3";

    public static final String GREETING = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    public static final String[] LIST_OF_OPTIONS = {
            "Choose an option by entering the associated number and pressing ENTER",
            OPT_LIST_OF_BOOKS + ". View list of books",
            OPT_CHECKOUT_BOOK + ". Checkout book",
            OPT_QUIT + ". Quit the application"};
    public static final String PROMPT_CHECKOUT = "Enter the index number of the book you want to checkout";
    public static final String SUCCESS_CHECKOUT = "Checkout successful";
    public static final String FAILURE_CHECKOUT_LOANED = "The selected book is already on loan.";
    public static final String FAILURE_CHECKOUT_NONEXISTENT = "There is no book with this index.";
    public static final String INVALID_INPUT = "Please select a valid option!";
    public static final String VALEDICTION = "Goodbye";
}
