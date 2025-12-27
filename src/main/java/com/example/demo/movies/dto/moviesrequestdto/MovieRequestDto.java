package com.example.demo.movies.dto.moviesrequestdto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class MovieRequestDto {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message="Language is required")
    private String language;

    @NotBlank(message = "Genres is required")
    private List<String> genres;

    private Integer durationInMinutes;

    private String postUrl;


}