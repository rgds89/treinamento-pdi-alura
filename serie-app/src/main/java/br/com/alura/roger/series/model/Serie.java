package br.com.alura.roger.series.model;

import br.com.alura.roger.series.model.enums.Genre;
import br.com.alura.roger.series.model.record.SerieData;
import lombok.Data;
import lombok.ToString;

import java.util.OptionalDouble;

@Data
@ToString
public class Serie {
    private String title;
    private Integer totalSeasons;
    private Double imdbRating;
    private Genre genre;
    private String actor;
    private String poster;
    private String plot;

    public Serie(SerieData serieData) {
        this.title = serieData.title();
        this.totalSeasons = serieData.totalSeasons();
        this.imdbRating = OptionalDouble.of(Double.valueOf(serieData.imdbRating())).orElse(0.0);
        this.genre = Genre.fromDescription(serieData.genre().split(",")[0].trim());
        this.actor = serieData.actor();
        this.poster = serieData.poster();
        this.plot = serieData.plot();
    }
}
