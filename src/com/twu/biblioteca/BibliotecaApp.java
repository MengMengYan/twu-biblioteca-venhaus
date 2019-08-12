package com.twu.biblioteca;

import java.util.List;
import java.util.Scanner;
import static com.twu.biblioteca.Text.*;

public class BibliotecaApp {
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        setupLibrary();
        app.start();
    }

    private static void setupLibrary() {
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

    private static String[] formatListOfBooks() {
        List<Book> bookList = Library.getBookList();
        String[] prettyListOfBooks = new String[bookList.size()];
        for(int i = 0; i < prettyListOfBooks.length; i++) {
            Book book = bookList.get(i);
            String str = book.getTitle() + " | "
                       + book.getAuthor() + " | "
                       + book.getYear();
            prettyListOfBooks[i] = str;
        }
        return prettyListOfBooks;
    }

    public void start() {
        System.out.println(GREETING);
        for (String option : LIST_OF_OPTIONS) {
            System.out.println(option);
        }

        Customer customer = new Customer();

        loop:
        while(scanner.hasNext()) {
            String input = scanner.next();
            switch (input) {
                case OPT_LIST_OF_BOOKS:
                    printArray(formatListOfBooks());
                    printArray(LIST_OF_OPTIONS);
                    break;
                case OPT_CHECKOUT_BOOK:
                    System.out.println(PROMPT_CHECKOUT);
                    if (scanner.hasNext()) {
                        input = scanner.next();
                        try {
                            customer.checkOutBook(Integer.parseInt(input));
                            System.out.println(SUCCESS_CHECKOUT);
                        } catch (BookUnavailableException | BookNonexistentException e) {
                            System.out.println(e.getMessage());
                        }
                        printArray(LIST_OF_OPTIONS);
                    }
                    break;
                case OPT_QUIT:
                    System.out.println(VALEDICTION);
                    break loop;
                default:
                    System.out.println(INVALID_INPUT);
                    printArray(LIST_OF_OPTIONS);
                    break;
            }
        }
    }

    private void printArray(String[] strArray) {
        for (String str : strArray) {
            System.out.println(str);
        }
    }
}