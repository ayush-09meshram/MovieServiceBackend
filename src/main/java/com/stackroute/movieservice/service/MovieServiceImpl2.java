package com.stackroute.movieservice.service;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieservice.exceptions.MovieNotFoundException;
import com.stackroute.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

@Qualifier("MovieServiceImpl2")
public class MovieServiceImpl2 implements MovieService {


    @Autowired
    MovieRepository movieRepository;

    @Override
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException {
        if(movieRepository.existsById(movie.getImdbId())){
            throw new MovieAlreadyExistsException("Movie already existsssssssssss");
        }
        movie = movieRepository.save(movie);
        if(movie == null){
            throw new MovieAlreadyExistsException("Movie already existsssssss");
        }
        return movie;
    }

    @Override
    public void deleteMovie(Movie movie) throws MovieNotFoundException {
        if(!(movieRepository.existsById(movie.getImdbId()))){
            throw new MovieNotFoundException("Movie not found");
        }
        movieRepository.delete(movie);
        if(movie == null){
            throw new MovieNotFoundException("Movie not found");
        }
    }

    @Override
    public Optional<Movie> getMovieByName(String movieTitle) throws MovieNotFoundException{
        Optional<Movie> movie = movieRepository.findBymovieTitle(movieTitle);
        if(movie.isPresent()) {
            return movie;
        }else {
            throw new MovieNotFoundException("MovieNotFoundException");
        }

    }


    @Override
    public Movie updateMovie(Movie movie) throws MovieNotFoundException{
        if(!(movieRepository.existsById(movie.getImdbId()))){
            throw new MovieNotFoundException("Movie not found");
        }
        movie = movieRepository.save(movie);
        if(movie == null){
            throw new MovieNotFoundException("Movie not found");
        }

        return movie;
    }

    @Override
    public List<Movie> getAllMovies() {

        return movieRepository.findAll();

    }
}
