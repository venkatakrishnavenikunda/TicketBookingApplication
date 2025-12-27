package com.example.demo.movies.exception;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(String id) {
        super("Movie with ID " + id + " not found");
    }
}
