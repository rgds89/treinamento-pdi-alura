package com.med.voll.api.dto.medico;

import com.med.voll.api.enums.Especialidade;
import com.med.voll.api.model.endereco.Endereco;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetalhamentoMedicoDTO {
    private Long id;
    private String nome;
    private String email;
    private Long crm;
    private Especialidade especialidade;
    private Endereco endereco;
}
