package br.com.alura.roger.series.model;

import br.com.alura.roger.series.model.record.EpisodeData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class Episode {
    private Integer season;
    private String title;
    private Integer episode;
    private Double imdbRating;
    private LocalDate released;

    public Episode(Integer season, EpisodeData episodeData) {
        this.season = season;
        this.title = episodeData.title();
        this.episode = episodeData.episode();
        try {
            this.imdbRating = Double.valueOf(episodeData.imdbRating());
        } catch (NumberFormatException e) {
            this.imdbRating = 0.0;
        }

        this.released = LocalDate.parse(episodeData.released());
    }
}
