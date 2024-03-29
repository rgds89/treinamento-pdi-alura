package br.com.alura.roger.series.model.record;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;


@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public record EpisodeData(@JsonAlias("Title") String title, @JsonAlias("Episode") Integer episode,
                          @JsonAlias("imdbRating") String imdbRating,
                          @JsonAlias("Released") String released) {
}
