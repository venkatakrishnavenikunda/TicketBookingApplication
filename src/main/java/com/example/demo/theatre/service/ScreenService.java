package com.example.demo.theatre.service;

import com.example.demo.theatre.dto.screenrequestdto.ScreenRequestDto;
import com.example.demo.theatre.dto.screenresponsedto.ScreenResponseDto;

import java.util.List;

public interface ScreenService {

    ScreenResponseDto createScreen(ScreenRequestDto dto);

    List<ScreenResponseDto> getScreensByTheatre(Long theatreId);

    ScreenResponseDto getScreenById(Long screenId);

    void deactivateScreen(Long screenId);
}
