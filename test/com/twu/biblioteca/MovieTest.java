package com.twu.biblioteca;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MovieTest {

    @Test
    public void movieHasRequiredFields() {
        Movie movie = new Movie();
        movie.setName("Avatar");
        movie.setYear(2013);
        movie.setDirector("James Cameron");
        movie.setRating(8);

        assertThat(movie.getName(), is("Avatar"));
        assertThat(movie.getYear(), is(2013));
        assertThat(movie.getDirector(), is("James Cameron"));
        assertThat(movie.getRating(), is(8));
    }
}
