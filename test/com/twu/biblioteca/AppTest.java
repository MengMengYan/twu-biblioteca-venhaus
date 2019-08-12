package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static com.twu.biblioteca.Text.*;

import static com.twu.biblioteca.TestHelpers.*;

public class AppTest {

    private final int GREETING_START        = 0;
    private final int GREETING_END          = GREETING_START + 1;
    private final int OPTIONS_LENGTH        = LIST_OF_OPTIONS.length;
    private final int OPTIONS_START         = GREETING_END;
    private final int OPTIONS_END           = OPTIONS_START + OPTIONS_LENGTH;
    private final int OPTIONS_ERROR_START   = OPTIONS_END;
    private final int OPTIONS_ERROR_END     = OPTIONS_ERROR_START + 1;
    private final int LIST_LENGTH           = LIST_OF_BOOKS.length;
    private final int LIST_START            = OPTIONS_END;
    private final int LIST_END              = LIST_START + LIST_LENGTH;

    @Before
    public void before() {
        setupOutput();
    }

    @Test
    public void applicationGreetsUserOnStartup() {
        String expected = GREETING;
        BibliotecaApp app = setupApp("");

        app.start();

        String[] result = getSubOutput(GREETING_START, GREETING_END);
        assertThat(result[0], is(expected));
    }

    @Test
    public void applicationDisplaysMenuOfOptions() {
        String[] expected = LIST_OF_OPTIONS;
        BibliotecaApp app = setupApp("");

        app.start();

        String[] result = getSubOutput(OPTIONS_START, OPTIONS_END);
        assertThat(result, is(expected));
    }

    @Test
    public void applicationShouldNotProgressIfNoOptionSelected() {
        int numberOfLinesDisplayed = OPTIONS_END;
        BibliotecaApp app = setupApp("");

        app.start();

        String appOutput = outStream.toString();
        String[] appOutputArr = appOutput.split("\n");
        assertThat(appOutputArr.length, is(numberOfLinesDisplayed));
    }
    @Test
    public void applicationShouldWarnUserIfLetterIsSelected() {
        String[] expected = {INVALID_INPUT};
        BibliotecaApp app = setupApp("z");

        app.start();

        String[] result = getSubOutput(OPTIONS_ERROR_START, OPTIONS_ERROR_END);
        assertThat(result, is(expected));
    }

    @Test
    public void applicationShouldWarnUserIfInvalidNumberIsSelected() {
        String[] expected = {INVALID_INPUT};
        BibliotecaApp app = setupApp("100");

        app.start();

        String[] result = getSubOutput(OPTIONS_ERROR_START, OPTIONS_ERROR_END);
        assertThat(result, is(expected));
    }

    @Test
    public void applicationShouldRepromptForUserInputOnInvalidInput() {
        String[] expected = LIST_OF_OPTIONS;
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

    @Test
    public void applicationDisplaysOptionsMenuAfterCorrectNonQuittingOption() {
        String[] expected = LIST_OF_OPTIONS;
        BibliotecaApp app = setupApp("1");

        app.start();

        String[] result = getLastOutputLines(OPTIONS_LENGTH);
        assertThat(result, is(expected));
    }

    @Test
    public void userCanQuitApplication() {
        String[] expected = {VALEDICTION};
        BibliotecaApp app = setupApp("2");

        app.start();
        String[] result = getLastOutputLines(1);
        assertThat(result, is(expected));
    }

}
