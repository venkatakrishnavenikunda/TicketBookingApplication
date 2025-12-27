package com.example.demo.movies.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "movies")
//@CompoundIndex(name = "title_language_genre_idx", def = "{'title' : 1, 'language': 1, 'genre': 1}")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class MovieDocument {

    @Id
    private String id;

    @Indexed
    private String title;

    private String language;
    private List<String> genres;
    private Integer durationInMinutes;
    private String postUrl;
}
