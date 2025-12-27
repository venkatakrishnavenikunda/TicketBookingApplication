package com.example.demo.movies.controller;

import com.example.demo.movies.dto.moviesrequestdto.MovieRequestDto;
import com.example.demo.movies.dto.moviesresponsedto.MovieResponseDto;
import com.example.demo.movies.service.MoviesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MoviesService moviesService;


    //Create  movie
    @PostMapping("/addMovie")
    public ResponseEntity<MovieResponseDto> createMovie(@RequestBody MovieRequestDto dto){
         MovieResponseDto responseDto=moviesService.createMovie(dto);
         return  new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


    //Get Movie by id
    @GetMapping("/getMoviesById/{id}")
    public ResponseEntity<MovieResponseDto> getMoviesBbyId(@PathVariable String id){
        return ResponseEntity.ok(moviesService.getMovieById(id));
    }

    //Get All movies - Pagination used here
    @GetMapping("/getAllMovies")
    public ResponseEntity<Page<MovieResponseDto>> getAllMovies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(moviesService.getAllMovies(page, size));
    }

    //Get Movies by language
    @GetMapping("/getMoviesByLanguage/{language}")
    public ResponseEntity<Page<MovieResponseDto>> getMoviesByLanguage(@PathVariable String language, @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(moviesService.getMoviesByLanguage(language,page,size));
    }

    //Get Movies by Genre
    @GetMapping("/getMoviesByGenre/{genre}")
    public ResponseEntity<Page<MovieResponseDto>> getMoviesByGenre(@RequestParam String genre, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(moviesService.getMoviesByGenre(genre,page,size));
    }

    //Search movie by title
    @GetMapping("/getMoviesByTitle/{title}")
    public ResponseEntity<Page<MovieResponseDto>> searchMoviesByTitle(@RequestParam String title, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(moviesService.searchMoviesByTitle(title, page, size));
    }

    //Update Movie
    @PutMapping("/updateMovie/{id}")
    public ResponseEntity<MovieResponseDto> updateMovie(@PathVariable String id, @RequestBody MovieRequestDto dto){
        return ResponseEntity.ok(moviesService.updateMovie(id,dto));
    }


    //Delete movie by ID
    @DeleteMapping("/deleteMovie/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable String id){
        return ResponseEntity.ok(moviesService.deleteMovie(id));
    }

    //Delete All movies
    @DeleteMapping("/deleteAllMovies")
    public ResponseEntity<String> deleteAllMovies(){
        return ResponseEntity.ok(moviesService.deleteAllMovies());
    }
}
