package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AppTest {

    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    private final PrintStream outOriginal = System.out;

    private final int GREETING_START        = 0;
    private final int GREETING_END          = GREETING_START + 1;
    private final int OPTIONS_LENGTH        = 2;
    private final int OPTIONS_START         = GREETING_END;
    private final int OPTIONS_END           = OPTIONS_START + OPTIONS_LENGTH;
    private final int OPTIONS_ERROR_START   = OPTIONS_END;
    private final int OPTIONS_ERROR_END     = OPTIONS_ERROR_START + 1;
    private final int LIST_LENGTH           = 3;
    private final int LIST_START            = OPTIONS_END;
    private final int LIST_END              = LIST_START + LIST_LENGTH;

    private final String[] LIST_OF_BOOKS = {
            "Book 1 | Author A | 2000",
            "Book 2 | Author B | 2001",
            "Book 3 | Author C | 2002"
    };

    @Before
    public void setupOutput() {
        System.setOut(new PrintStream(outStream));
    }

    @After
    public void restoreOutput() {
        System.setOut(outOriginal);
    }

    @Test
    public void applicationGreetsUserOnStartup() {
        String expected = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
        BibliotecaApp app = setupApp("");

        app.start();

        String[] result = getSubOutput(GREETING_START, GREETING_END);
        assertThat(result[0], is(expected));
    }

    @Test
    public void applicationDisplaysMenuOfOptions() {
        String[] expected = {
                "Choose an option by entering the associated number and pressing ENTER",
                "1. List of books"
        };
        BibliotecaApp app = setupApp("");

        app.start();

        String[] result = getSubOutput(OPTIONS_START, OPTIONS_END);
        assertThat(result, is(expected));
    }

    @Test
    public void applicationShouldNotProgressIfNoOptionSelected() {
        int numberOfLinesDisplayed = OPTIONS_END;
        BibliotecaApp app = new BibliotecaApp();

        app.start();

        String appOutput = outStream.toString();
        String[] appOutputArr = appOutput.split("\n");
        assertThat(appOutputArr.length, is(numberOfLinesDisplayed));
    }

    @Test
    public void applicationShouldWarnUserIfLetterIsSelected() {
        String[] expected = {"Please select a valid option!"};
        BibliotecaApp app = setupApp("z");

        app.start();

        String[] result = getSubOutput(OPTIONS_ERROR_START, OPTIONS_ERROR_END);
        assertThat(result, is(expected));
    }

    @Test
    public void applicationShouldWarnUserIfInvalidNumberIsSelected() {
        String[] expected = {"Please select a valid option!"};
        BibliotecaApp app = setupApp("100");

        app.start();

        String[] result = getSubOutput(OPTIONS_ERROR_START, OPTIONS_ERROR_END);
        assertThat(result, is(expected));
    }

    @Test
    public void applicationShouldRepromptForUserInputOnInvalidInput() {
        String[] expected = {
                "Choose an option by entering the associated number and pressing ENTER",
                "1. List of books"
        };
        BibliotecaApp app = setupApp("invalid");

        app.start();

        String[] result = getSubOutput(OPTIONS_ERROR_END, OPTIONS_ERROR_END + OPTIONS_LENGTH);
        assertThat(result, is(expected));
    }

    @Test
    public void applicationShouldAcceptUserInputAfterInvalidInputWarning() {
        String[] expected = LIST_OF_BOOKS;
        BibliotecaApp app = setupApp("invalid\n1");

        app.start();

        String[] result = getSubOutput(OPTIONS_ERROR_END + OPTIONS_LENGTH,
                                        OPTIONS_ERROR_END + OPTIONS_LENGTH + LIST_LENGTH);
        assertThat(result, is(expected));
    }

    @Test
    public void userCanViewListOfBooksWithAuthorAndPublicationYear() {
        String[] expected = LIST_OF_BOOKS;
        BibliotecaApp app = setupApp("1");

        app.start();

        String[] result = getSubOutput(LIST_START, LIST_END);
        assertThat(result, is(expected));
    }

    private String[] getSubOutput(int from, int to) {
        String appOutput = outStream.toString();
        String[] appOutputArr = appOutput.split("\n");
        String[] result = Arrays.copyOfRange(appOutputArr, from, to);
        return result;
    }

    private BibliotecaApp setupApp(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        return new BibliotecaApp();
    }

}
