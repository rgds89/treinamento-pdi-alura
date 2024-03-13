package br.com.alura.roger.series.model.record;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.NoArgsConstructor;


@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public record SerieData(@JsonAlias("Title") String title, @JsonAlias("totalSeasons") Integer totalSeasons,
                        @JsonAlias("imdbRating") String imdbRating, @JsonAlias("Genre") String genre,
                        @JsonAlias("Actors") String actor, @JsonAlias("Poster") String poster,
                        @JsonAlias("Plot") String plot) {
}
