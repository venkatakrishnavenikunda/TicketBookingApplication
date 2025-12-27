package com.example.demo.theatre.dto.screenresponsedto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class ScreenResponseDto {

    private Long id;
    private String name;
    private Integer capacity;
    private Long theatreId;
    private Boolean active;
    private LocalDateTime createdAt;
}
