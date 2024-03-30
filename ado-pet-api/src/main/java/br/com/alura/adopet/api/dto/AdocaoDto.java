package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.StatusAdocao;

import java.time.LocalDateTime;

public record AdocaoDto(Long id, LocalDateTime data, TutorDto tutor, PetDto pet, String motivo, StatusAdocao status,
                        String justificativaStatus) {
}
