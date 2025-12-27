package com.example.demo.movies.dto.moviesresponsedto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class MovieResponseDto {

    private String title;
    private String language;
    private List<String> genres;
    private Integer durationInMinutes;
    private String postUrl;
}
