package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static com.twu.biblioteca.Text.*;

import static com.twu.biblioteca.TestHelpers.*;

public class CLITest {

    private final String[] LIST_OF_TEST_BOOKS = {
            "0. Book 0 | Roy | 2004",
            "1. Book 1 | Paul | 2018",
            "2. Book 2 | Mengmeng | 2018"
    };

    private final int GREETING_START        = 0;
    private final int GREETING_END          = GREETING_START + 1;
    private final int OPTIONS_LENGTH        = LIST_OF_OPTIONS.length;
    private final int OPTIONS_START         = GREETING_END;
    private final int OPTIONS_END           = OPTIONS_START + OPTIONS_LENGTH;
    private final int OPTIONS_ERROR_START   = OPTIONS_END;
    private final int OPTIONS_ERROR_END     = OPTIONS_ERROR_START + 1;
    private final int LIST_LENGTH           = LIST_OF_TEST_BOOKS.length;
    private final int LIST_START            = OPTIONS_END;
    private final int LIST_END              = LIST_START + LIST_LENGTH;

    @Before
    public void before() {
        setupOutput();
        Library.reset();
        LibraryTest.addTestBooks();
    }

    @Test
    public void applicationGreetsUserOnStartup() {
        BibliotecaApp app = setupApp("");

        app.start();

        String[] result = getSubOutput(GREETING_START, GREETING_END);
        assertThat(result[0], is(GREETING));
    }

    @Test
    public void applicationDisplaysMenuOfOptions() {
        BibliotecaApp app = setupApp("");

        app.start();

        String[] result = getSubOutput(OPTIONS_START, OPTIONS_END);
        assertThat(result, is(LIST_OF_OPTIONS));
    }

    @Test
    public void applicationShouldNotProgressIfNoOptionSelected() {
        BibliotecaApp app = setupApp("");

        app.start();

        String appOutput = outStream.toString();
        String[] appOutputArr = appOutput.split("\n");
        assertThat(appOutputArr.length, is(OPTIONS_END));
    }
    @Test
    public void applicationShouldWarnUserIfLetterIsSelected() {
        BibliotecaApp app = setupApp("z");

        try {
            app.start();
        } catch (NoSuchElementException e) {}

        String[] result = getSubOutput(OPTIONS_ERROR_START, OPTIONS_ERROR_END);
        String[] expected = {FAILURE_NON_POSINT_INDEX};
        assertThat(result, is(expected));
    }

    @Test
    public void applicationShouldWarnUserIfInvalidNumberIsSelected() {
        BibliotecaApp app = setupApp("100");

        app.start();

        String[] result = getSubOutput(OPTIONS_ERROR_START, OPTIONS_ERROR_END);
        String[] expected = {INVALID_INPUT};
        assertThat(result, is(expected));
    }

    @Test
    public void applicationShouldRepromptForUserInputOnInvalidInput() {
        BibliotecaApp app = setupApp("invalid");

        try {
            app.start();
        } catch (NoSuchElementException e) { }

        String[] result = getLastOutputLines(1);
        assertThat(result[0], is(FAILURE_NON_POSINT_INDEX));
    }

    @Test
    public void applicationShouldAcceptUserInputAfterInvalidInputWarning() {
        BibliotecaApp app = setupApp("invalid\n1");

        app.start();

        String[] tempResult = getLastOutputLines(8);
        String[] result = Arrays.copyOfRange(tempResult, 0, 3);
        assertThat(result, is(LIST_OF_TEST_BOOKS));
    }

    @Test
    public void userCanViewListOfBooksWithAuthorAndPublicationYear() {
        BibliotecaApp app = setupApp(OPT_LIST_OF_BOOKS);

        app.start();

        String[] result = getSubOutput(LIST_START, LIST_END);
        assertThat(result, is(LIST_OF_TEST_BOOKS));
    }

    @Test
    public void applicationOnlyShowsAvailableBooks() {
        BibliotecaApp app = setupApp(OPT_CHECKOUT_BOOK + "\n" + 2 + "\n"
                                   + OPT_LIST_OF_BOOKS);


        app.start();

        String[] resultTemp = getLastOutputLines(7);
        String[] result = Arrays.copyOfRange(resultTemp, 0, 2);
        String[] expected = Arrays.copyOfRange(LIST_OF_TEST_BOOKS, 0, 2);
        assertThat(result, is(expected));
    }

    @Test
    public void userSeesPromptForBookCheckout() {
        BibliotecaApp app = setupApp(OPT_CHECKOUT_BOOK);

        try {
            app.start();
        } catch (NoSuchElementException e) {}

        String[] result = getLastOutputLines(1);
        assertThat(result[0], is(PROMPT_CHECKOUT));
    }

    @Test
    public void userSeesMessageOnSuccessfulCheckout() {
        BibliotecaApp app = setupApp(OPT_CHECKOUT_BOOK + "\n" + 0);

        app.start();

        // Get lines of options + preceding success message
        String[] resultTemp = getLastOutputLines(OPTIONS_LENGTH + 1);
        // Extract first line (success message)
        String result = resultTemp[0];
        assertThat(result, is(SUCCESS_CHECKOUT));
    }

    @Test
    public void userSeesMessageWhenTryingToCheckoutUnavailableBook() {
        BibliotecaApp app = setupApp(OPT_CHECKOUT_BOOK + "\n" + 0 + "\n" +
                                     OPT_CHECKOUT_BOOK + "\n" + 0);

        app.start();

        // Get lines of options + preceding failure message
        String[] resultTemp = getLastOutputLines(OPTIONS_LENGTH + 1);
        // Extract first line (failure message)
        String result = resultTemp[0];
        assertThat(result, is(FAILURE_CHECKOUT_LOANED));
    }

    @Test
    public void userSeesMessageWhenTryingToCheckoutNonexistentBook() {
        BibliotecaApp app = setupApp(OPT_CHECKOUT_BOOK + "\n" + 1000);

        app.start();

        // Get lines of options + preceding failure message
        String[] resultTemp = getLastOutputLines(OPTIONS_LENGTH + 1);
        // Extract first line (failure message)
        String result = resultTemp[0];
        assertThat(result, is(FAILURE_CHECKOUT_NONEXISTENT));
    }

    @Test
    public void userSeesMessageWhenTryingToCheckoutWithNonIntegerIndex() {
        BibliotecaApp app = setupApp(OPT_CHECKOUT_BOOK + "\n" + "text");

        try {
            app.start();
        } catch (NoSuchElementException e) { }

        // Get lines of options + preceding failure message
        String[] resultTemp = getLastOutputLines(1);
        // Extract first line (failure message)
        String result = resultTemp[0];
        assertThat(result, is(FAILURE_NON_POSINT_INDEX));
    }

    @Test
    public void userSeesPromptForBookReturn() {
        BibliotecaApp app = setupApp(OPT_RETURN_BOOK);

        try {
            app.start();
        } catch (NoSuchElementException e) { }

        String[] result = getLastOutputLines(1);
        assertThat(result[0], is(PROMPT_RETURN));
    }

    @Test
    public void userSeesMessageOnSuccessfulReturn() {
        BibliotecaApp app = setupApp(OPT_CHECKOUT_BOOK + "\n" + 1 + "\n"
                                   + OPT_RETURN_BOOK + "\n" + 0);

        app.start();

        // Get lines of options + preceding success message
        String[] resultTemp = getLastOutputLines(OPTIONS_LENGTH + 1);
        // Extract first line (success message)
        String result = resultTemp[0];
        assertThat(result, is(SUCCESS_RETURN));
    }

    @Test
    public void userSeesMessageWhenTryingToCheckoutBookNotLoanedByThem() {
        BibliotecaApp app = setupApp(OPT_RETURN_BOOK + "\n" + 100);

        app.start();

        // Get lines of options + preceding failure message
        String[] resultTemp = getLastOutputLines(OPTIONS_LENGTH + 1);
        // Extract first line (failure message)
        String result = resultTemp[0];
        assertThat(result, is(FAILURE_RETURN_BOOK_NOT_LOANED_BY_CUSTOMER));
    }

    @Test
    public void userSeesMessageWhenTryingToReturnWithNonIntegerIndex() {
        BibliotecaApp app = setupApp(OPT_RETURN_BOOK + "\n" + "text");

        try {
            app.start();
        } catch (NoSuchElementException e) { }

        // Get lines of options + preceding failure message
        String[] resultTemp = getLastOutputLines(1);
        // Extract first line (failure message)
        String result = resultTemp[0];
        assertThat(result, is(FAILURE_NON_POSINT_INDEX));
    }

    @Test
    public void applicationDisplaysOptionsMenuAfterCorrectNonQuittingOption() {
        BibliotecaApp app = setupApp(OPT_LIST_OF_BOOKS);

        app.start();

        String[] result = getLastOutputLines(OPTIONS_LENGTH);
        assertThat(result, is(LIST_OF_OPTIONS));

        app = setupApp(OPT_CHECKOUT_BOOK + "\n" + 0);

        app.start();

        result = getLastOutputLines(OPTIONS_LENGTH);
        assertThat(result, is(LIST_OF_OPTIONS));
    }

    @Test
    public void userCanQuitApplication() {
        BibliotecaApp app = setupApp(OPT_QUIT);

        app.start();
        String[] result = getLastOutputLines(1);
        assertThat(result[0], is(VALEDICTION));
    }

}
