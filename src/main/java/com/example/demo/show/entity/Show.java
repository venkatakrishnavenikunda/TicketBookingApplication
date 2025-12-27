package com.example.demo.show.entity;

import com.example.demo.theatre.entity.Screen;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="shows", indexes = {@Index(name = "idx_show_movie", columnList = "movieId"),
        @Index(name = "idx_show_start", columnList = "startTime"),
        @Index(name = "idx_show_screen", columnList = "screen_id")})
@Getter
@Setter

public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String movieId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Double price;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="screen_id")
    @JsonIgnore
    private Screen screen;

}
