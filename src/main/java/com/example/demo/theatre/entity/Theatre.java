package com.example.demo.theatre.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String locationId; //MongoDB reference

    @OneToMany
    private List<Screen> screens;

}