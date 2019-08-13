package com.twu.biblioteca;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/*

TODO https://martinfowler.com/articles/practical-test-pyramid.html

Yes, you should test the public interface. More importantly, however, you don't test trivial code. Don't worry,
Kent Beck said it's ok. You won't gain anything from testing simple getters or setters or other trivial implementations
(e.g. without any conditional logic). Save the time, that's one more meeting you can attend, hooray!

==============>

Maybe check out if you can go for a functional test to see how things work together

 */
public class BookTest {

    @Test
    public void bookHasTitle() {
        Book book = new Book();
        book.setTitle("TDD for Dummies");
        assertThat(book.getTitle(), is("TDD for Dummies"));
    }

    @Test
    public void bookHasAuthor() {
        Book book = new Book();
        book.setAuthor("Paul Venhaus");
        assertThat(book.getAuthor(), is("Paul Venhaus"));
    }

    @Test
    public void bookHasPublicationYear() {
        Book book = new Book();
        book.setYear(2019);
        assertThat(book.getYear(), is(2019));
    }

    @Test
    public void bookLoanStatusCanBeChanged() {
        Book book = new Book();
        assertThat(book.isAvailable(), is(true));

        book.setAvailable(false);
        assertThat(book.isAvailable(), is(false));

        book.setAvailable(true);
        assertThat(book.isAvailable(), is(true));
    }
}
