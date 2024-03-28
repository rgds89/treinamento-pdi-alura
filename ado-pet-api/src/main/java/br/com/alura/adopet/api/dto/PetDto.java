package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.TipoPet;
import lombok.Builder;

@Builder
public record PetDto(Long id, TipoPet tipo, String nome, String raca, Integer idade, String cor, Float peso, Boolean adotado) {
}
