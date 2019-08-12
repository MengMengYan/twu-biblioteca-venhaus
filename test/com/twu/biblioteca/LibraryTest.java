package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.BookNonexistentException;
import com.twu.biblioteca.exceptions.BookUnavailableException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LibraryTest {

    @Before
    public void resetLibrary() {
        Library.reset();
    }

    @Test
    public void libraryCanAddBooks() {
        Book newBook = new Book();
        Library.addBook(newBook);
    }

    @Test
    public void libraryCanAddMovies() {
        Movie newMovie = new Movie();
        Library.addMovie(newMovie);
    }

    @Test
    public void libraryCanBeReset() {
        addTestBooks();
        addTestMovies();
        Library.reset();
        assertThat(Library.getBookList().size(), is(0));
        assertThat(Library.getMovieList().size(), is(0));
    }

    @Test
    public void libraryCanReturnListOfBooks() {
        Book book1 = new Book();
        book1.setAuthor("Pablo");
        book1.setTitle("Book 1");
        book1.setYear(2018);
        Book book2 = new Book();
        book1.setAuthor("Mengmeng");
        book1.setTitle("Book 2");
        book1.setYear(2018);
        Library.addBook(book1);
        Library.addBook(book2);
        ArrayList<Book> expected = new ArrayList<>();
        expected.add(book1);
        expected.add(book2);

        List<Book> books = Library.getBookList();

        assertThat(books, is(expected));
    }

    @Test
    public void libraryCanReturnListOfMovies() {
        Movie movie0 = new Movie();
        movie0.setName("Movie 0");
        movie0.setDirector("Quentin Tarrantino");
        movie0.setYear(2017);
        movie0.setRating(9);
        Library.addMovie(movie0);

        Movie movie1 = new Movie();
        movie0.setName("Movie 1");
        movie0.setDirector("Steven Spielberg");
        movie0.setYear(1980);
        movie0.setRating(7);
        Library.addMovie(movie1);

        Movie movie2 = new Movie();
        movie0.setName("Movie 2");
        movie0.setDirector("Roland Herzog");
        movie0.setYear(1982);
        movie0.setRating(4);
        Library.addMovie(movie2);

        ArrayList<Movie> expected = new ArrayList<>();
        expected.add(movie0);
        expected.add(movie1);
        expected.add(movie2);
        assertThat(Library.getMovieList(), is(expected));
    }

    @Test
    public void libraryCanLoanOutAvailableBook() {
        Book book1 = new Book();
        book1.setAuthor("Pablo");
        book1.setTitle("Book 1");
        book1.setYear(2018);
        Library.addBook(book1);

        Book loanedBook = Library.loanBook(0);

        assertThat(loanedBook, is(book1));
        assertThat(loanedBook.isAvailable(), is(false));
    }

    @Test(expected = BookUnavailableException.class)
    public void libraryCannotLoanOutUnavailableBook() {
        Book book1 = new Book();
        book1.setAuthor("Pablo");
        book1.setTitle("Book 1");
        book1.setYear(2018);
        book1.setAvailable(false);
        Library.addBook(book1);

        Library.loanBook(0);
    }

    @Test(expected = BookNonexistentException.class)
    public void libraryCannotLoanOutNonexistentBook() {
        Library.loanBook(0);
    }

    static void addTestBooks() {
        Book book0 = new Book();
        book0.setAuthor("Roy");
        book0.setTitle("Book 0");
        book0.setYear(2004);
        Library.addBook(book0);

        Book book1 = new Book();
        book1.setAuthor("Paul");
        book1.setTitle("Book 1");
        book1.setYear(2018);
        Library.addBook(book1);

        Book book2 = new Book();
        book2.setAuthor("Mengmeng");
        book2.setTitle("Book 2");
        book2.setYear(2018);
        Library.addBook(book2);
    }

    static void addTestMovies() {
        Movie movie0 = new Movie();
        movie0.setName("Movie 0");
        movie0.setDirector("Quentin Tarrantino");
        movie0.setYear(2017);
        movie0.setRating(9);
        Library.addMovie(movie0);

        Movie movie1 = new Movie();
        movie0.setName("Movie 1");
        movie0.setDirector("Steven Spielberg");
        movie0.setYear(1980);
        movie0.setRating(7);
        Library.addMovie(movie1);

        Movie movie2 = new Movie();
        movie0.setName("Movie 2");
        movie0.setDirector("Roland Herzog");
        movie0.setYear(1982);
        movie0.setRating(4);
        Library.addMovie(movie2);
    }
}
