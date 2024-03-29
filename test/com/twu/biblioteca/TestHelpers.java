package com.twu.biblioteca;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class TestHelpers {

    static ByteArrayOutputStream outStream;

    //TODO setOut changes the standard output for the entire JVM. This can have unexpected side effects.
    // That is not necessary. Try to have a look into Dependency Injections.
     static void setupOutput() {
        outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
    }

    static String[] getSubOutput(int from, int to) {
        String[] appOutputArr = getSplitOutput();
        String[] result = Arrays.copyOfRange(appOutputArr, from, to);
        return result;
    }

    static String[] getLastOutputLines(int numLines) {
        String[] appOutputArr = getSplitOutput();
        String[] result = Arrays.copyOfRange(appOutputArr,
                appOutputArr.length - numLines, appOutputArr.length);
        return result;
    }

    static String[] getSplitOutput() {
        String appOutput = outStream.toString();
        return appOutput.split("\n");
    }

    static BibliotecaApp setupApp(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        //TODO similarly to above but checking out for one of them is fine
        System.setIn(inputStream);
        return new BibliotecaApp();
    }

    static BibliotecaApp setupApp(int input) {
        return setupApp("" + input);
    }
}
