package com.stackroute.movieservice.exceptions;

public class MovieAlreadyExistsException extends Exception {
    private String message;

    public MovieAlreadyExistsException(){

    }

    public MovieAlreadyExistsException(String message){
        super(message);
        this.message = message;
    }
}
