package br.com.alura.roger.series.model;

import br.com.alura.roger.series.model.record.EpisodeData;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.OptionalDouble;

@Data
@ToString
@NoArgsConstructor
@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer episode;
    private Double imdbRating;
    private LocalDate released;
    @ManyToOne
    private Season season;

    public Episode(Season season, EpisodeData episodeData) {
        this.season = season;
        this.title = episodeData.title();
        this.episode = episodeData.episode();
        this.imdbRating = OptionalDouble.of(Double.valueOf(episodeData.imdbRating())).orElse(0.0);
        try {
            this.released = LocalDate.parse(episodeData.released());
        } catch (DateTimeParseException e) {
            if (episodeData.released().equals("N/A") || episodeData.released().equals("null")) {
                this.released = null;
            } else {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", java.util.Locale.ENGLISH);
                    this.released = sdf.parse(episodeData.released()).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                } catch (ParseException e2) {
                    this.released = null;
                }
            }
        }
    }
}
