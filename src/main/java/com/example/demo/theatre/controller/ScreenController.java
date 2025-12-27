package com.example.demo.theatre.controller;

import com.example.demo.theatre.dto.screenrequestdto.ScreenRequestDto;
import com.example.demo.theatre.dto.screenresponsedto.ScreenResponseDto;
import com.example.demo.theatre.service.ScreenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/screen")
public class ScreenController {
    private final ScreenService screenService;

    //Create screen
    @PostMapping("/createScreen")
    public ResponseEntity<ScreenResponseDto> createScreen(@Valid @RequestBody ScreenRequestDto dto) {
        ScreenResponseDto response = screenService.createScreen(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //Get Screen By theatre
    @GetMapping("/getTheatre/{id}")
    public ResponseEntity<List<ScreenResponseDto>> getScreensByTheatre(@PathVariable Long id) {
        List<ScreenResponseDto> screens = screenService.getScreensByTheatre(id);
        return ResponseEntity.ok(screens);
    }

    //Get screen By id
    @GetMapping("/getScreens/{id}")
    public ResponseEntity<ScreenResponseDto> getScreenById(@PathVariable Long id) {
        ScreenResponseDto response = screenService.getScreenById(id);
        return ResponseEntity.ok(response);
    }

    //Deactivate Screen
    @DeleteMapping("/{screenId}")
    public ResponseEntity<Void> deactivateScreen(@PathVariable Long screenId) {
        screenService.deactivateScreen(screenId);
        return ResponseEntity.noContent().build();
    }
}
