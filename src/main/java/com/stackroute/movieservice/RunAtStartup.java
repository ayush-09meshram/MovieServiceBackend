/*
package com.stackroute.movieservice;
import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationListener;

@Component
public class RunAtStartup implements ApplicationListener<ContextRefreshedEvent> {
@Autowired
    MovieRepository movieRepository;
    */
/**
     * This method is called during Spring's startup.
     *
     * @param event Event raised when an ApplicationContext gets initialized or
     * refreshed.
     *//*

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        System.out.println("ApplicationListener Invoked At Spring Container Startup");
        Movie movie = new Movie();
        movie.setImdbId(1);
        movie.setMovieTitle("Hum");
        movie.setRating(3);
        movie.setYearOfRelease("2017");
        movieRepository.save(movie);
        // here your code ...

        return;
    }

}*/
