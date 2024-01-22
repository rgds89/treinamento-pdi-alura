package br.com.alura.roger.series.model.record;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SerieData(@JsonAlias("Title") String title, @JsonAlias("totalSeasons") Double totalSeasons, @JsonAlias("imdbRating") String imdbRating) {
}
