package br.com.alura.roger.series.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@Data
@Entity
@Table(name = "seasons")
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer season;
    private Integer totalSeasons;
    private String title;
    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episode> episodes;
    private Double imdbRating;
    @ManyToOne
    private Serie serie;

    public Season(Integer season, Integer totalSeasons, String title, Double imdbRating, Serie serie) {
        this.season = season;
        this.totalSeasons = totalSeasons;
        this.title = title;
        this.imdbRating = imdbRating;
        this.serie = serie;
    }

    @Override
    public String toString() {
        return "Season{" +
                "season=" + season +
                ", totalSeasons=" + totalSeasons +
                ", title='" + title + '\'' +
                ", imdbRating=" + imdbRating +
                '}';
    }
}
