package com.example.demo.movies.repository;

import com.example.demo.movies.document.MovieDocument;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<MovieDocument, String> {

    // Pagination + filtering
    Page<MovieDocument> findByLanguage(String language, Pageable pageable);

    Page<MovieDocument> findByGenres(String genre, Pageable pageable);

    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    Page<MovieDocument> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    boolean existsByTitleAndLanguage(String title, String language); // for idempotency


}
