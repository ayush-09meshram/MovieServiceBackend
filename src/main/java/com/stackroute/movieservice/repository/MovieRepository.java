package com.stackroute.movieservice.repository;

import com.stackroute.movieservice.domain.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie,Integer> {
    public Optional<Movie> findBymovieTitle(String movieTitle);
    public Optional<Movie> findByimdbId(int imdbId);
}
