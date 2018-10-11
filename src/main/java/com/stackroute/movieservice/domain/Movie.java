package com.stackroute.movieservice.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Moviesz")
public class Movie {
    @Id
    @ApiModelProperty(notes = "The database generated product ID")

    private int imdbId;
    @ApiModelProperty(notes = "The database generated movieTitle")

    private String movieTitle;
    @ApiModelProperty(notes = "The database generated url")

    private String url;
    @ApiModelProperty(notes = "The database generated rating")

    private float rating;
    @ApiModelProperty(notes = "The database generated yearOfRelease")

    private String yearOfRelease;

    public Movie(){

    }

    public Movie(int imdbId, String movieTitle, String url, float rating, String yearOfRelease) {
        this.imdbId = imdbId;
        this.movieTitle = movieTitle;
        this.url = url;
        this.rating = rating;
        this.yearOfRelease = yearOfRelease;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "imdbId=" + imdbId +
                ", movieTitle='" + movieTitle + '\'' +
                ", url='" + url + '\'' +
                ", rating=" + rating +
                ", yearOfRelease='" + yearOfRelease + '\'' +
                '}';
    }

    public int getImdbId() {
        return imdbId;
    }

    public void setImdbId(int imdbId) {
        this.imdbId = imdbId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }
}
