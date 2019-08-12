package com.twu.biblioteca;

import java.util.Scanner;
import static com.twu.biblioteca.Text.*;

public class BibliotecaApp {
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        app.start();
    }

    public void start() {
        System.out.println(GREETING);
        for (String option : LIST_OF_OPTIONS) {
            System.out.println(option);
        }

        while(scanner.hasNext()) {
            String input = scanner.next();
            if (input.equals(OPT_LIST_OF_BOOKS)) {
                printArray(LIST_OF_BOOKS);
                printArray(LIST_OF_OPTIONS);
            } else if (input.equals(OPT_QUIT)){
                System.out.println(VALEDICTION);
                break;
            } else {
                System.out.println(INVALID_INPUT);
                printArray(LIST_OF_OPTIONS);
            }
        }
    }

    private void printArray(String[] strArray) {
        for (String str : strArray) {
            System.out.println(str);
        }
    }
}