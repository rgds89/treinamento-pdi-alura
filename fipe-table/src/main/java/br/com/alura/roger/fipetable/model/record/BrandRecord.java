package br.com.alura.roger.fipetable.model.record;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BrandRecord(@JsonAlias("nome") String name, @JsonAlias("codigo") String code) {
}
