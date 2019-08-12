package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CustomerTest {

    @Before
    public void before() {
        Library.reset();
        LibraryTest.addTestBooks();
    }

    @Test
    public void customerCanCheckoutAvailableBook() {
        Customer customer = new Customer();
        Book book1 = Library.getBook(1);
        ArrayList<Book> expected = new ArrayList<>();
        expected.add(book1);

        customer.checkOutBook(1);

        assertThat(customer.getBookList(), is(expected));
    }

    @Test(expected = BookUnavailableException.class)
    public void customerCannotCheckoutUnavailableBook() {
        Customer customer = new Customer();
        Book book1 = Library.getBook(1);
        book1.setAvailable(false);

        customer.checkOutBook(1);
    }
}
