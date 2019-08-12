package com.twu.biblioteca;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

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


}
