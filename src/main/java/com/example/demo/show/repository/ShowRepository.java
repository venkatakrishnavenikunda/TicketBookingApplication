package com.example.demo.show.repository;

import com.example.demo.show.entity.Show;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {

    Page<Show> findByMovieId(String movieId, Pageable pageable);

    Page<Show> findByScreen_Id(Long screenId, Pageable pageable);

    @Query("""
       SELECT s FROM Show s
       WHERE s.screen.id = :screenId
         AND (:startTime < s.endTime AND :endTime > s.startTime)
""")
    List<Show> findConflictingShows(
            @Param("screenId") Long screenId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);

}
