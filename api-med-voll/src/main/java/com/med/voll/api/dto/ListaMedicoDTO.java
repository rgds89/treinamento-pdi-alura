package com.med.voll.api.dto;

import com.med.voll.api.enums.Especialidade;
import com.med.voll.api.model.Medico;

public record ListaMedicoDTO (String nome, String email, Long crm, Especialidade especialidade){
    public ListaMedicoDTO(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
