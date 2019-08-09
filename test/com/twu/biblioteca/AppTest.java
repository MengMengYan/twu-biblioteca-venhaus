package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AppTest {

    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    private final PrintStream outOriginal = System.out;

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

        String[] result = getPartOfResultFromAppOutput(0,1);
        assertThat(result[0], is(expected));
    }

    @Test
    public void userCanViewListOfBooksWithAuthorAndPublicationYear() {
        String[] expected = {
                "Book 1 | Author A | 2000",
                "Book 2 | Author B | 2001",
                "Book 3 | Author C | 2002"
        };
        BibliotecaApp app = new BibliotecaApp();

        app.start();

        String[] result = getPartOfResultFromAppOutput(1, 4);
        assertThat(result, is(expected));
    }

    private String[] getPartOfResultFromAppOutput(int from, int to) {
        String appOutput = outStream.toString();
        String[] appOutputArr = appOutput.split("\n");
        String[] result = Arrays.copyOfRange(appOutputArr, from, to);
        return result;
    }
}
