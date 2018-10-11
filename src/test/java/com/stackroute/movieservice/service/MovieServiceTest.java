package com.stackroute.movieservice.service;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieservice.exceptions.MovieNotFoundException;
import com.stackroute.movieservice.repository.MovieRepository;
import com.stackroute.movieservice.service.MovieServiceImpl2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovieServiceTest {

    Movie movie;

    //Create a mock for UserRepository
    @Mock//test double
            MovieRepository movieRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    MovieServiceImpl2 movieServiceImpl2;
    List<Movie> list = null;


    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        movie = new Movie();
        movie.setMovieTitle("John");
        movie.setImdbId(101);
        movie.setYearOfRelease("2017");
        movie.setRating(10);
        list = new ArrayList<>();
        list.add(movie);
        movieRepository.save(movie);


    }

    @Test
    public void saveUserTestSuccess() throws MovieAlreadyExistsException {

        when(movieRepository.save((Movie) any())).thenReturn(movie);
        Movie savedMovie = movieServiceImpl2.saveMovie(movie);
        Assert.assertEquals(movie, savedMovie);

        //verify here verifies that movieRepository save method is only called once
/*
        verify(movieRepository, times(1)).save(movie);
*/

    }

    @Test(expected = MovieAlreadyExistsException.class)
    public void saveUserTestFailure() throws MovieAlreadyExistsException {
        when(movieRepository.save((Movie) any())).thenReturn(null);
        Movie savedMovie = movieServiceImpl2.saveMovie(movie);
        System.out.println("savedUser" + savedMovie);
        Assert.assertEquals(movie, savedMovie);
//add verify
       /*doThrow(new UserAlreadyExistException()).when(movieRepository).findById(eq(101));
       movieServiceImpl2.saveUser(user);*/


    }

    @Test
    public void getAllMovie() {

        movieRepository.save(movie);
        //stubbing the mock to return specific data
        when(movieRepository.findAll()).thenReturn(list);
        List<Movie> movieList = movieServiceImpl2.getAllMovies();
        Assert.assertEquals(list, movieList);

        //add verify
    }



    @Test
    public void searchByName() throws MovieNotFoundException {
        when(movieRepository.findBymovieTitle(movie.getMovieTitle())).thenReturn(Optional.of(movie));
        Optional savedMovie = movieServiceImpl2.getMovieByName(movie.getMovieTitle());
        Assert.assertEquals(Optional.of(movie), savedMovie);
    }

    @Test(expected = MovieNotFoundException.class)
    public void searchByNameFailure() throws MovieNotFoundException {
        when(movieRepository.findBymovieTitle(movie.getMovieTitle())).thenReturn(Optional.of(movie));
        Movie updatedMovie = new Movie();
        updatedMovie.setMovieTitle("Joh");
        updatedMovie.setImdbId(11);
        updatedMovie.setYearOfRelease("Jeny");
        updatedMovie.setRating(1);
        movieRepository.save(updatedMovie);
        Optional updatedMoviE = movieServiceImpl2.getMovieByName(updatedMovie.getMovieTitle());
        Assert.assertEquals(Optional.of(movie),updatedMoviE);
    }

    @Test
    public void updateMovie() throws MovieNotFoundException {
        Movie updatedMovie = new Movie();
        updatedMovie.setMovieTitle("John");
        updatedMovie.setImdbId(101);
        updatedMovie.setYearOfRelease("Jenny");
        updatedMovie.setRating(10);
        movieRepository.save(updatedMovie);
        when(movieRepository.save((Movie) any())).thenReturn(movie);
        updatedMovie = movieServiceImpl2.updateMovie(movie);
        Assert.assertEquals(movie, updatedMovie);
    }

    @Test(expected = MovieNotFoundException.class)
    public void updateMovieFailure() throws MovieNotFoundException {
        movieRepository.save(movie);
        Movie updatedMovie = new Movie();
        updatedMovie = movie;
        movieRepository.deleteAll();
        updatedMovie.setMovieTitle("Joh");
        updatedMovie.setImdbId(102);
        updatedMovie.setYearOfRelease("2018");
        updatedMovie.setRating(10);
        movieRepository.save(updatedMovie);
        Movie updatedMoviE = movieServiceImpl2.updateMovie(updatedMovie);
        System.out.println(updatedMoviE);
        Assert.assertNotSame(movie, updatedMoviE);
    }

    @Test
    public void deleteMovie() throws MovieNotFoundException{
        movieRepository.save(movie);
        movieServiceImpl2.deleteMovie(movie);
        Optional deletedMovie = movieServiceImpl2.getMovieByName(movie.getMovieTitle());
        Assert.assertEquals(Optional.empty(),deletedMovie);
    }

    @Test(expected = MovieNotFoundException.class)
    public void deleteMovieFailure() throws MovieNotFoundException{
        Movie movie1 = movie;
        movieRepository.save(movie);
        movieServiceImpl2.deleteMovie(movie);
        Assert.assertNotSame(movie,movieRepository.findBymovieTitle(movie.getMovieTitle()));
    }

}
