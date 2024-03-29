package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.BookNonexistentException;
import com.twu.biblioteca.exceptions.BookNotLoanedByCustomerException;
import com.twu.biblioteca.exceptions.BookUnavailableException;
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
    public void customerCanCheckoutAvailableBook() throws BookNonexistentException, BookUnavailableException {
        Customer customer = new Customer();
        Book book1 = Library.getBook(1);

        customer.checkOutBook(1);

        ArrayList<Book> expected = new ArrayList<>();
        expected.add(book1);
        assertThat(customer.getBookList(), is(expected));
    }

    @Test(expected = BookUnavailableException.class)
    public void customerCannotCheckoutUnavailableBook() throws BookNonexistentException, BookUnavailableException {
        Customer customer = new Customer();
        Book book1 = Library.getBook(1);
        book1.setAvailable(false);

        customer.checkOutBook(1);
    }

    @Test
    public void customerCanReturnLoanedBook() throws BookNonexistentException, BookUnavailableException, BookNotLoanedByCustomerException {
        Customer customer = new Customer();
        Book book1 = Library.getBook(1);
        ArrayList<Book> expected = new ArrayList<>();
        expected.add(book1);

        customer.checkOutBook(1);
        customer.returnBook(0);

        assertThat(customer.getBookList().size(), is(0));
        assertThat(book1.isAvailable(), is(true));
    }

    @Test(expected = BookNotLoanedByCustomerException.class)
    public void customerCannotReturnBookThatIsNotLoanedByThem() throws BookNotLoanedByCustomerException {
        Customer customer = new Customer();
        customer.returnBook(1);
    }
}
