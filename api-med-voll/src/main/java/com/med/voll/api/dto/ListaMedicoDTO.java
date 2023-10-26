package com.med.voll.api.dto;

import com.med.voll.api.enums.Especialidade;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListaMedicoDTO {
    private String nome;
    private String email;
    private Long crm;
    private Especialidade especialidade;
}
