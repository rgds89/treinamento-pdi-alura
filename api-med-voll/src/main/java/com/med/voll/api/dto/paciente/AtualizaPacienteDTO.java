package com.med.voll.api.dto.paciente;

import com.med.voll.api.dto.endereco.AtualizaEnderecoDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AtualizaPacienteDTO {
    @NotNull
    private Long id;
    private String nome;
    private String telefone;
    private AtualizaEnderecoDTO endereco;
}
