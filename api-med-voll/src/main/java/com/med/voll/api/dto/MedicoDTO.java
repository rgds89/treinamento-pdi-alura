package com.med.voll.api.dto;

import com.med.voll.api.enums.Especialidade;
import com.med.voll.api.model.Endereco;
import lombok.Data;

@Data
public class MedicoDTO {
    private String nome;
    private String email;
    private Long  crm;
    private Especialidade especialidade;
    private EnderecoDTO endereco;
}
