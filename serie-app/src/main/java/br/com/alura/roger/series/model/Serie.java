package br.com.alura.roger.series.model;

import br.com.alura.roger.series.model.enums.Genre;
import br.com.alura.roger.series.model.record.SerieData;
import br.com.alura.roger.series.service.ConsumerChatGPT;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.ArrayList;
import java.util.OptionalDouble;

@Data
@ToString
@NoArgsConstructor
@Entity
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    private Integer totalSeasons;
    private Double imdbRating;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private String actor;
    private String poster;
    private String plot;
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Season> seasons = new ArrayList<>();

    public Serie(SerieData serieData) {
        this.title = serieData.title();
        this.totalSeasons = serieData.totalSeasons();
        this.imdbRating = OptionalDouble.of(Double.valueOf(serieData.imdbRating())).orElse(0.0);
        this.genre = Genre.fromDescription(serieData.genre().split(",")[0].trim());
        this.actor = serieData.actor();
        this.poster = serieData.poster();
        this.plot = ConsumerChatGPT.getTranslation(serieData.plot()).trim();
    }
}
