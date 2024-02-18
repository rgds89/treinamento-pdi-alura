package br.com.alura.roger.fipetable.model.record;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record ModelsYearsRecord(@JsonAlias("modelos") List<ModelRecord> models, @JsonAlias("anos") List<YearRecord> years) {
}
