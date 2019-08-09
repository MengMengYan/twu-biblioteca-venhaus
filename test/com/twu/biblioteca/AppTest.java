package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

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
        String greeting = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";
        BibliotecaApp app = new BibliotecaApp();

        app.start();

        assertThat(outStream.toString(), is(greeting));
    }

    @Test
    public void userCanViewListOfBooks() {
        String[] listOfBooks = {"Book 1", "Book 2", "Book 3"};
        BibliotecaApp app = new BibliotecaApp();

        app.start();

        String appOutput = outStream.toString();
        String[] appOutputArr = appOutput.split("\n");
        String[] result = Arrays.copyOfRange(appOutputArr, 1, 4);
        assertThat(listOfBooks, is(result));
    }
}
