package com.example.demo.show.controller;

import com.example.demo.show.dto.showrequestdto.ShowRequestDto;
import com.example.demo.show.dto.showresponsedto.ShowResponseDto;
import com.example.demo.show.service.ShowService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/shows")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    //Create Show
    @PostMapping("/createShow")
    public ResponseEntity<ShowResponseDto> createShow(@RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey,
            @Valid @RequestBody ShowRequestDto dto) {

        log.info("Received request to create show with movieId={}", dto.getMovieId());
        ShowResponseDto response = showService.createShow(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    //GET SHOW BY ID
    @GetMapping("/getShow/{showId}")
    public ResponseEntity<ShowResponseDto> getShowById(
            @PathVariable Long showId) {

        log.debug("Received request to fetch show by id={}", showId);
        ShowResponseDto response = showService.getShowById(showId);
        return ResponseEntity.ok(response);
    }

    // GET ALL SHOWS (PAGINATION) 
    @GetMapping("/getAllShows")
    public ResponseEntity<List<ShowResponseDto>> getAllShows(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.debug("Received request to fetch all shows page={}, size={}", page, size);
        List<ShowResponseDto> shows = showService.getAllShows(page, size); // <-- get list
        return ResponseEntity.ok(shows); // <-- return list, not Page
    }


    //GET SHOWS BY MOVIE
    @GetMapping("/getShowsByMovie/{movieId}")
    public ResponseEntity<Page<ShowResponseDto>> getShowsByMovie(@PathVariable String movieId, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.debug("Received request to fetch shows by movieId={}", movieId);
        Page<ShowResponseDto> response = showService.getShowsByMovie(movieId, page, size);
        return ResponseEntity.ok(response);
    }

    //GET SHOWS BY SCREEN
    @GetMapping("/getShowsByScreen/{screenId}")
    public ResponseEntity<Page<ShowResponseDto>> getShowsByScreen(@PathVariable Long screenId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        log.debug("Received request to fetch shows by screenId={}", screenId);
        Page<ShowResponseDto> response = showService.getShowsByScreen(screenId, page, size);
        return ResponseEntity.ok(response);
    }

    //UPDATE SHOW
    @PutMapping("/updateShow/{showId}")
    public ResponseEntity<ShowResponseDto> updateShow(
            @PathVariable Long showId,
            @Valid @RequestBody ShowRequestDto dto) {

        log.info("Received request to update show id={}", showId);
        ShowResponseDto response = showService.updateShow(showId, dto);
        return ResponseEntity.ok(response);
    }

    //DELETE SHOW
    @DeleteMapping("/deleteShow/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id) {

        log.warn("Received request to delete show id={}", id);
        showService.deleteShow(id);
        return ResponseEntity.noContent().build();
    }
}
