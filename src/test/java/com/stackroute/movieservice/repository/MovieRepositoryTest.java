package com.stackroute.movieservice.repository;

import com.stackroute.movieservice.domain.Movie;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


//This is integrated test we need DB server
@RunWith(SpringRunner.class)
@DataMongoTest
//@SpringBootTest
public class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;
    Movie movie;


    @Before
    public void setUp() {
        movie = new Movie();
        movie.setMovieTitle("Hum aapke hein kaun?");
        movie.setImdbId(1);
        movie.setRating(4);
        movie.setYearOfRelease("2018");
        movieRepository.save(movie);

    }

    @After
    public void tearDown(){

        movieRepository.deleteAll();
    }


    @Test
    public void testSaveUser() {
        movieRepository.save(movie);
        Movie fetchUser = movieRepository.findById(movie.getImdbId()).get();
        Assert.assertEquals(1, fetchUser.getImdbId());

    }

    @Test
    public void testSaveUserFailure() {
        movieRepository.save(movie);
        Movie fetchUser = movieRepository.findById(movie.getImdbId()).get();
        Assert.assertNotSame(movie, fetchUser);
    }

    @Test
    public void testGetAllUser() {

        Movie movie1 = new Movie(1, "Tere Naam", "URL", 4, "2002");
        movieRepository.save(movie1);

        List<Movie> list = movieRepository.findAll();
        Assert.assertEquals("Tere Naam", list.get(0).getMovieTitle());


    }
}
