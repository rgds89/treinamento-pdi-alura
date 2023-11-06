package com.med.voll.api.dto.paciente;

import com.med.voll.api.model.endereco.Endereco;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetalhamentoPacienteDTO {
    private Long id;
    private String nome;
    private String email;
    private Endereco endereco;
}
