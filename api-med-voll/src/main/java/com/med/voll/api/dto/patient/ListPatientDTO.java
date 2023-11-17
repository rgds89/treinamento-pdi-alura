package com.med.voll.api.dto.patient;

import com.med.voll.api.model.patient.Patient;

public record ListPatientDTO(Long id, String nome, String email, String cpf) {
    public ListPatientDTO(Patient patient) {
        this(patient.getId(), patient.getNome(), patient.getEmail(), patient.getCpf());
    }
}
