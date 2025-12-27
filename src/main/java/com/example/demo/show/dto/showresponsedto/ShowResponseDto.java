package com.example.demo.show.dto.showresponsedto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ShowResponseDto {

    private Long id;
    private String movieId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double price;
    private Long screenId;
}
