package br.com.alura.roger.series.business.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Endpoint {
    URL("http://www.omdbapi.com/?t="), API_KEY("&apikey=21568e41"), SEASON("&season="), EPISODE("&episode=");

    private final String description;
}
