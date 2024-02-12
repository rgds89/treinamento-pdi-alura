package br.com.alura.roger.series.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;


@AllArgsConstructor
@Data
@ToString
public class Season {
    private Integer season;
    private Integer totalSeasons;
    private String title;
    private List<Episode> episodes;
    private Double imdbRating;
}
