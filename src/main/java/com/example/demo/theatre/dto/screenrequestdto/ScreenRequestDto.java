package com.example.demo.theatre.dto.screenrequestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ScreenRequestDto {

    @NotBlank(message = "Screen name is required")
    private String name;

    @NotNull(message = "Capacity is required")
    @Positive(message = "Capacity must be greater than zero")
    private Integer capacity;

    @NotNull(message = "Theatre ID is required")
    private Long theatreId;

    private Boolean active = true;
}