package com.example.demo.show.service;

import com.example.demo.show.dto.showrequestdto.ShowRequestDto;
import com.example.demo.show.dto.showresponsedto.ShowResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ShowService {

    // CREATE show
    ShowResponseDto createShow(ShowRequestDto dto);


    // Get show by Id
    ShowResponseDto getShowById(Long showId);


    //Get all shows
    List<ShowResponseDto> getAllShows(int page, int size);


    //Get Shows By movie
    Page<ShowResponseDto> getShowsByMovie(
            String movieId, int page, int size);


    //Get shows by screen
    Page<ShowResponseDto> getShowsByScreen(
            Long screenId, int page, int size);


    // UPDATE show by id
    ShowResponseDto updateShow(Long showId, ShowRequestDto dto);


    // DELETE show by id
    void deleteShow(Long showId);
}
