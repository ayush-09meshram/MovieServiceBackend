package com.stackroute.movieservice.service;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieservice.repository.MovieRepository;
import com.stackroute.movieservice.service.MovieServiceImpl2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

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
    List<Movie> list= null;


    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        movie = new Movie();
        movie.setMovieTitle("John");
        movie.setImdbId(101);
        movie.setYearOfRelease("Jenny");
        movie.setRating(10);
        list = new ArrayList<>();
        list.add(movie);


    }

    @Test
    public void saveUserTestSuccess() throws MovieAlreadyExistsException {

        when(movieRepository.save((Movie) any())).thenReturn(movie);
        Movie savedMovie = movieServiceImpl2.saveMovie(movie);
        Assert.assertEquals(movie,savedMovie);

        //verify here verifies that movieRepository save method is only called once
        verify(movieRepository,times(1)).save(movie);

    }

    @Test(expected = MovieAlreadyExistsException.class)
    public void saveUserTestFailure() throws MovieAlreadyExistsException {
        when(movieRepository.save((Movie) any())).thenReturn(null);
        Movie savedMovie = movieServiceImpl2.saveMovie(movie);
        System.out.println("savedUser" + savedMovie);
        Assert.assertEquals(movie,savedMovie);
//add verify
       /*doThrow(new UserAlreadyExistException()).when(movieRepository).findById(eq(101));
       movieServiceImpl2.saveUser(user);*/


    }

    @Test
    public void getAllMovie(){

        movieRepository.save(movie);
        //stubbing the mock to return specific data
        when(movieRepository.findAll()).thenReturn(list);
        List<Movie> movieList = movieServiceImpl2.getAllMovies();
        Assert.assertEquals(list,movieList);

        //add verify
    }





}
