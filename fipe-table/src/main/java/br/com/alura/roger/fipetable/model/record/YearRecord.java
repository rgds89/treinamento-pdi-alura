package br.com.alura.roger.fipetable.model.record;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record YearRecord(@JsonAlias("codigo") String code, @JsonAlias("nome") String name){
}
