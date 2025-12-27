package com.example.demo.movies.service;

import com.example.demo.movies.dto.moviesrequestdto.MovieRequestDto;
import com.example.demo.movies.dto.moviesresponsedto.MovieResponseDto;
import org.springframework.data.domain.Page;


public interface MoviesService {

    // CREATE
    MovieResponseDto createMovie(MovieRequestDto dto);

    //Create bulk movies
   // List<MovieResponseDto> createMoviesBulk(List<MovieRequestDto> dtos);

    // Get Movies by Id
    MovieResponseDto getMovieById(String id);

    //get All movies
    Page<MovieResponseDto> getAllMovies(int page, int size);

    //Get movies by language
    Page<MovieResponseDto> getMoviesByLanguage(String language, int page, int size);

    //Get movies by genre
    Page<MovieResponseDto> getMoviesByGenre(String genre, int page, int size);

    //Get movies by title
    Page<MovieResponseDto> searchMoviesByTitle(String title, int page, int size);

    // Update movie
    MovieResponseDto updateMovie(String id, MovieRequestDto dto);

    // DELETE movie by id
    String deleteMovie(String id);

    //Delete all movies
    String deleteAllMovies();
}
