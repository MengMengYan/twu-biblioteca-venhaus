package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.NonPosIntIndexException;

import java.util.*;

import static com.twu.biblioteca.Text.*;

public class CLI {

    private Scanner scanner = new Scanner(System.in);

    static void showGreeting() {
        System.out.println(GREETING);
    }

    static void showListOfOptions() {
        for (String option : LIST_OF_OPTIONS) {
            System.out.println(option);
        }
    }

    static void showListOfBooks() {
        printList(formatListOfBooks());
        printArray(LIST_OF_OPTIONS);
    }

    static void showError(String message) {
        System.out.println(message);
    }

    static void showCheckoutPrompt() {
        System.out.println(PROMPT_CHECKOUT);
    }

    static void showReturnPrompt() {
        System.out.println(PROMPT_RETURN);
    }

    static void showCheckoutSuccessMessage() {
        System.out.println(SUCCESS_CHECKOUT);
    }

    static void showReturnSuccessMessage() {
        System.out.println(SUCCESS_RETURN);
    }

    static void showValediction() {
        System.out.println(VALEDICTION);
    }

    static void showInvalidInputWarning() {
        System.out.println(INVALID_INPUT);
    }

    boolean isSessionActive() {
        return scanner.hasNext();
    }


    static List<String> formatListOfBooks() {
        List<Book> bookList = Library.getBookList();
        List<String> bookStringList = new ArrayList<>();
        for(int i = 0; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            if (!book.isAvailable()) {
                continue;
            }
            String str = i + ". "
                    + book.getTitle() + " | "
                    + book.getAuthor() + " | "
                    + book.getYear();
            bookStringList.add(str);
        }
        return bookStringList;
    }


    int getIndex() {
        int bookIdx;
        try {
            bookIdx = getPositiveInteger();
        } catch (NonPosIntIndexException e) {
            CLI.showError(e.getMessage());
            bookIdx = getIndex();
        }
        return bookIdx;
    }

    private int getPositiveInteger() throws NonPosIntIndexException {
        int bookIdx;
        try {
            bookIdx = Integer.parseInt(scanner.next());
            if (bookIdx < 0) {
                throw new NonPosIntIndexException(FAILURE_NON_POSINT_INDEX);
            }
        } catch (NumberFormatException e) {
            throw new NonPosIntIndexException(FAILURE_NON_POSINT_INDEX);
        }
        return bookIdx;
    }

    static void printArray(String[] strArray) {
        for (String str : strArray) {
            System.out.println(str);
        }
    }

    static void printList(List<String> strList) {
        for (String str : strList) {
            System.out.println(str);
        }
    }
}
