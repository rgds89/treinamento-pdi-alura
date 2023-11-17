package com.med.voll.api.dto.doctor;

import com.med.voll.api.enums.Specialties;
import com.med.voll.api.model.doctor.Doctor;


public record ListaMedicoDTO (Long id, String nome, String email, Long crm, Specialties specialties){
    public ListaMedicoDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getNome(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialties());
    }
}
