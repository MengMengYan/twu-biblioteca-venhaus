package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.BookNonexistentException;
import com.twu.biblioteca.exceptions.BookNotLoanedByCustomerException;
import com.twu.biblioteca.exceptions.BookUnavailableException;

import static com.twu.biblioteca.Text.*;

public class BibliotecaApp {

    private static CLI cli;

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        AppSetup.setupLibrary();
        app.start();
    }

    public void start() {
        Customer customer = new Customer();
        cli = new CLI();

        cli.showGreeting();
        cli.showListOfOptions();

        loop:
        while(cli.isSessionActive()) {
            int selection = cli.getIndex();
            switch (selection) {
                case OPT_LIST_OF_BOOKS:
                    cli.showListOfBooks();
                    break;
                case OPT_CHECKOUT_BOOK:
                    startCheckoutProcess(customer);
                    break;
                case OPT_RETURN_BOOK:
                    startReturnProcess(customer);
                    break;
                case OPT_QUIT:
                    cli.showValediction();
                    break loop;
                default:
                    cli.showInvalidInputWarning();
                    cli.printArray(LIST_OF_OPTIONS);
                    break;
            }
        }
    }

    private void startCheckoutProcess(Customer customer) {
        cli.showCheckoutPrompt();
        int bookIdx = cli.getIndex();
        try {
            customer.checkOutBook(bookIdx);
            cli.showCheckoutSuccessMessage();
        } catch (BookUnavailableException | BookNonexistentException e) {
            cli.showError(e.getMessage());
        } finally {
            cli.showListOfOptions();
        }
    }

    private void startReturnProcess(Customer customer) {
        cli.showReturnPrompt();
        int bookIdx = cli.getIndex();
        try {
            customer.returnBook(bookIdx);
            cli.showReturnSuccessMessage();
        } catch (BookNotLoanedByCustomerException e) {
            cli.showError(e.getMessage());
        } finally {
            cli.showListOfOptions();
        }
    }
}