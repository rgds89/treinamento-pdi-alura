package br.com.alura.adopet.api.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record AbrigoDto(Long id, String nome, String telefone, String email, List<PetDto>pets) {
}
