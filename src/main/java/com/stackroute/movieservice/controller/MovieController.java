package com.stackroute.movieservice.controller;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieservice.exceptions.MovieNotFoundException;
import com.stackroute.movieservice.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/")
@Api(value="Moviez", description="Operations pertaining to products in Movie Database!")
public class MovieController {

    @Autowired
    @Qualifier("MovieServiceImpl2")
    MovieService movieService;


    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @PostMapping("movie")
    @ApiOperation(value = "Save a movie")
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie){
        ResponseEntity responseEntity;
        try{
            movieService.saveMovie(movie);
            responseEntity = new ResponseEntity<String>("Successfully saved", HttpStatus.CREATED);
        } catch (MovieAlreadyExistsException ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PutMapping("movie")
    @ApiOperation(value = "Update a movie")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie){
        ResponseEntity responseEntity;
        try{
            movieService.updateMovie(movie);
            responseEntity = new ResponseEntity<String>("Successfully updated", HttpStatus.OK);
        } catch (MovieNotFoundException ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }

    @DeleteMapping("movie")
    @ApiOperation(value = "Delete a movie")
    public ResponseEntity<?> deleteMovie(@RequestBody Movie movie){
        ResponseEntity responseEntity;
        try{
            movieService.deleteMovie(movie);
            responseEntity = new ResponseEntity<String>("Successfully deleted", HttpStatus.GONE);
        } catch (MovieNotFoundException ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.METHOD_NOT_ALLOWED);
        }
        return responseEntity;
    }

    @GetMapping("getMovieByName/{movieTitle}")
    @ApiOperation(value = "Find a movie")
    public ResponseEntity<?>getMovieByName(@PathVariable String movieTitle){
        Optional<Movie> movies;
        try {
            movies = movieService.getMovieByName(movieTitle);
            return new ResponseEntity<Optional<Movie>>(movies,HttpStatus.OK);
        } catch (MovieNotFoundException ex) {
            return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation(value = "View a list of available movies!", response = Iterable.class)
    @GetMapping("getAllMovies")
    public ResponseEntity<?> getAllMovies(){

        return new ResponseEntity<Iterable<Movie>>(movieService.getAllMovies(),HttpStatus.OK);

    }

}
