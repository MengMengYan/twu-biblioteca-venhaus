package com.twu.biblioteca;

public class Text {
    public static final String OPT_LIST_OF_BOOKS = "1";
    public static final String OPT_CHECKOUT_BOOK = "2";
    public static final String OPT_RETURN_BOOK = "3";
    public static final String OPT_QUIT = "4";

    public static final String GREETING = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

    public static final String[] LIST_OF_OPTIONS = {
            "Choose an option by entering the associated number and pressing ENTER",
            OPT_LIST_OF_BOOKS + ". View list of books",
            OPT_CHECKOUT_BOOK + ". Checkout book",
            OPT_RETURN_BOOK   + ". Return book",
            OPT_QUIT + ". Quit the application"};

    public static final String PROMPT_CHECKOUT = "Enter the index number of the book you want to checkout.";
    public static final String PROMPT_RETURN = "Enter the index number of the book you want to return.";

    public static final String SUCCESS_RETURN = "Thank you for returning the book";
    public static final String SUCCESS_CHECKOUT = "Thank you! Enjoy the book";

    public static final String FAILURE_CHECKOUT_LOANED = "Sorry, that book is not available";
    public static final String FAILURE_CHECKOUT_NONEXISTENT = "There is no book with this index.";
    public static final String FAILURE_RETURN_BOOK_NOT_LOANED_BY_CUSTOMER = "THat is not a valid book to return.";


    public static final String INVALID_INPUT = "Please select a valid option!";
    public static final String VALEDICTION = "Goodbye.";
}
