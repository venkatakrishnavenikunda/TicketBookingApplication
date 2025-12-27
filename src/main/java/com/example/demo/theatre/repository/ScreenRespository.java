package com.example.demo.theatre.repository;

import com.example.demo.theatre.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScreenRespository extends JpaRepository<Screen, Long> {

    /*Optional<Screen> findByIdAndActiveTrue(Long id);
    boolean existsByIdAndActiveTrue(Long id);


    List<Screen> findByTheatre_IdAndActiveTrue(Long theatreId);
    List<Screen> findByActiveTrue();*/

    // Check idempotency: screen name should be unique within a theatre
    boolean existsByNameAndTheatre_Id(String name, Long theatreId);

    // Fetch only active screens for a theatre
    List<Screen> findByTheatre_IdAndActiveTrue(Long theatreId);

}
