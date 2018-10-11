package com.stackroute.movieservice.repository;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
import java.util.Optional;


//This is integrated test we need DB server
@RunWith(SpringRunner.class)
@DataMongoTest
//@SpringBootTest
public class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;
    Movie movie;
    Movie movie1;

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

    @Test
    public void updateUser(){
        Movie updatedMovie = new Movie();
        updatedMovie.setMovieTitle("John");
        updatedMovie.setImdbId(101);
        updatedMovie.setYearOfRelease("Jenny");
        updatedMovie.setRating(10);
        movie = updatedMovie;
        movieRepository.save(movie);
        Assert.assertEquals(movie,updatedMovie);
    }

    @Test
    public void updateUserFailure(){
        Movie movie1 = movie;
        movieRepository.save(movie1);
        Movie updatedMovie = new Movie();
        updatedMovie.setMovieTitle("John");
        updatedMovie.setImdbId(101);
        updatedMovie.setYearOfRelease("Jenny");
        updatedMovie.setRating(10);
        movie = updatedMovie;
        movieRepository.save(movie);
        Assert.assertNotSame(movie,movie1);
    }

    @Test
    public void searchMovieByName() throws Exception{

        Assert.assertEquals(Optional.of(movie), movieRepository.findBymovieTitle(movie.getMovieTitle()));
    }

    @Test
    public void searchMovieByNameFailure() throws Exception{

        Movie movie2 = new Movie();
        movie2.setMovieTitle("Hum pke hein kaun?");
        movie2.setImdbId(1);
        movie2.setRating(4);
        movie2.setYearOfRelease("2018");
        movieRepository.save(movie2);

        Assert.assertNotSame(Optional.of(movie), movieRepository.findBymovieTitle(movie2.getMovieTitle()));
    }

    @Test
    public void deleteMovie() throws Exception{
        movieRepository.delete(movie);
        Assert.assertEquals(Optional.empty(),movieRepository.findBymovieTitle(movie.getMovieTitle()));
    }

    @Test
    public void deleteMovieFailure() throws Exception{
        Movie movie1 = movie;
        movieRepository.delete(movie);
        Assert.assertNotSame(movie,movieRepository.findBymovieTitle(movie1.getMovieTitle()));
    }
}
