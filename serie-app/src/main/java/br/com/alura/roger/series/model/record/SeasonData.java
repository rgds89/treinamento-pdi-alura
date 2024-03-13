package br.com.alura.roger.series.model.record;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public record SeasonData(@JsonAlias("Title") String title, @JsonAlias("Season") Integer season,
                         @JsonAlias("Episodes") List<EpisodeData> episodes,
                         @JsonAlias("totalSeasons") Integer totalSeasons) {
}
