package com.stackroute.movieservice.service;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieservice.exceptions.MovieNotFoundException;
import com.stackroute.movieservice.repository.MovieRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MovieService {
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException;
    public Movie updateMovie(Movie movie) throws MovieNotFoundException;
    public void deleteMovie(Movie movie) throws MovieNotFoundException;
    public Optional<Movie> getMovieByName(String movieTitle) throws MovieNotFoundException;
    public Iterable<Movie> getAllMovies();
}
