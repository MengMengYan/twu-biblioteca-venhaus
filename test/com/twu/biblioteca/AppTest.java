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

    private final int GREETING_START = 0;
    private final int GREETING_END = GREETING_START + 1;
    private final int OPTIONS_START = GREETING_END;
    private final int OPTIONS_END = OPTIONS_START + 2;
    private final int LIST_START = OPTIONS_END;
    private final int LIST_END = LIST_START + 3;

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
        BibliotecaApp app = new BibliotecaApp();

        app.start();

        String[] result = getPartOfResultFromAppOutput(GREETING_START, GREETING_END);
        assertThat(result[0], is(expected));
    }

    @Test
    public void applicationDisplaysMenuOfOptions() {
        String[] expected = {
                "Choose an option by entering the associated number and pressing ENTER",
                "1. List of books"
        };
        BibliotecaApp app = new BibliotecaApp();

        app.start();

        String[] result = getPartOfResultFromAppOutput(OPTIONS_START, OPTIONS_END);
        assertThat(result, is(expected));
    }

    @Test
    public void userCanViewListOfBooksWithAuthorAndPublicationYear() {
        String[] expected = {
                "Book 1 | Author A | 2000",
                "Book 2 | Author B | 2001",
                "Book 3 | Author C | 2002"
        };
        setupInput("1\n");
        BibliotecaApp app = new BibliotecaApp();

        app.start();

        String[] result = getPartOfResultFromAppOutput(LIST_START, LIST_END);
        assertThat(result, is(expected));
    }

    private String[] getPartOfResultFromAppOutput(int from, int to) {
        String appOutput = outStream.toString();
        String[] appOutputArr = appOutput.split("\n");
        String[] result = Arrays.copyOfRange(appOutputArr, from, to);
        return result;
    }

    public static void setupInput(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        BibliotecaApp.openInput();
    }

}
