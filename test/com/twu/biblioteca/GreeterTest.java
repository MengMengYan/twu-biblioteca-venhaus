package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GreeterTest {

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
        String greeting = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
        BibliotecaApp app = new BibliotecaApp();

        app.start();

        assertThat(outStream.toString(), is(greeting));
    }
}
