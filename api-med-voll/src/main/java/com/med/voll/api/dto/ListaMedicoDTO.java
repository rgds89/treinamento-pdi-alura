package com.med.voll.api.dto;

import com.med.voll.api.enums.Especialidade;
import com.med.voll.api.model.Medico;

public record ListaMedicoDTO (Long id, String nome, String email, Long crm, Especialidade especialidade){
    public ListaMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
