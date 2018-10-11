/*
package com.stackroute.movieservice;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CommandAtStartup implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(CommandAtStartup.class);
    @Autowired
    MovieRepository movieRepository;

    @Override
    public void run(String...args) throws Exception {
        logger.info("Application started with command-line arguments: {} . \n To kill this application, press Ctrl + C.", Arrays.toString(args));
        Movie movie = new Movie();
        movie.setYearOfRelease("2018");
        movie.setRating(3);
        movie.setImdbId(22);
        movie.setMovieTitle("The Mum");
        movieRepository.save(movie);
    }
}*/
