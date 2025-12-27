package com.example.demo.movies.service.serviceImp;

import com.example.demo.movies.document.MovieDocument;
import com.example.demo.movies.dto.moviesrequestdto.MovieRequestDto;
import com.example.demo.movies.dto.moviesresponsedto.MovieResponseDto;
import com.example.demo.movies.exception.MovieNotFoundException;
import com.example.demo.movies.repository.MovieRepository;
import com.example.demo.movies.service.MoviesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MoviesServiceImpl implements MoviesService {

    private final MovieRepository movieRepository;

    // Convert Movie to MovieResponseDto
    private MovieResponseDto mapToDto(MovieDocument movie) {
        return new MovieResponseDto(
                movie.getId(), movie.getTitle(), movie.getGenres(), movie.getDurationInMinutes(),
                movie.getLanguage());
    }

    //Create Movie
    @Override
    public MovieResponseDto createMovie(MovieRequestDto dto) {
        MovieDocument movie = new MovieDocument();
        movie.setTitle(dto.getTitle());
        movie.setGenres(dto.getGenres());
        movie.setLanguage(dto.getLanguage());
        movie.setDurationInMinutes(dto.getDurationInMinutes());
        MovieDocument saved = movieRepository.save(movie);
        return mapToDto(saved);
    }


    //Get Movie By Id
    @Override
    public MovieResponseDto getMovieById(String id) {
        MovieDocument movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        return mapToDto(movie);
    }


    //Get ALl Movies
    @Override
    public Page<MovieResponseDto> getAllMovies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("title").ascending());
        Page<MovieDocument> movies = movieRepository.findAll(pageable);
        List<MovieResponseDto> dtos = movies.stream().map(this::mapToDto).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, movies.getTotalElements());
    }

    //Get Movies by Language
    @Override
    public Page<MovieResponseDto> getMoviesByLanguage(String language, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("title").ascending());
        Page<MovieDocument> movies = movieRepository.findByLanguage(language, pageable);
        List<MovieResponseDto> dtos = movies.stream().map(this::mapToDto).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, movies.getTotalElements());
    }


    //Get Movies by Genre
    @Override
    public Page<MovieResponseDto> getMoviesByGenre(String genre, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("title").ascending());
        Page<MovieDocument> movies = movieRepository.findByGenres(genre, pageable);
        List<MovieResponseDto> dtos = movies.stream().map(this::mapToDto).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, movies.getTotalElements());
    }

    //Search Movies by Title
    @Override
    public Page<MovieResponseDto> searchMoviesByTitle(String title, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("title").ascending());
        Page<MovieDocument> movies = movieRepository.findByTitleContainingIgnoreCase(title, pageable);
        List<MovieResponseDto> dtos = movies.stream().map(this::mapToDto).collect(Collectors.toList());
        return new PageImpl<>(dtos,pageable,movies.getTotalElements());
    }


    //Update Movie by id
    @Override
    public MovieResponseDto updateMovie(String id, MovieRequestDto dto) {
        MovieDocument movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        movie.setTitle(dto.getTitle());
        movie.setGenres(dto.getGenres());
        movie.setLanguage(dto.getLanguage());
        movie.setDurationInMinutes(dto.getDurationInMinutes());
        movie.setPostUrl(dto.getPostUrl());
        MovieDocument save = movieRepository.save(movie);
        return mapToDto(save);
    }


    //Delete movie by id
    @Override
    public String deleteMovie(String id) {
        MovieDocument movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        movieRepository.delete(movie);
        return "Movie with Id"+ id+" deleted successfully";
    }

    //Delete all movies
    @Override
    public String deleteAllMovies() {
        movieRepository.deleteAll();
        return "All movies deleted successfully";
    }
}


