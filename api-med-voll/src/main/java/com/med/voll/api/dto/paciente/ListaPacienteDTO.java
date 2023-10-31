package com.med.voll.api.dto.paciente;

import com.med.voll.api.model.paciente.Paciente;

public record ListaPacienteDTO(Long id, String nome, String email, String cpf) {
    public ListaPacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
