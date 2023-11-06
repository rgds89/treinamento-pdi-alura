package com.med.voll.api.dto.medico;

import com.med.voll.api.dto.endereco.AtualizaEnderecoDTO;
import com.med.voll.api.dto.telefone.AtualizaTelefoneDTO;
import com.med.voll.api.model.endereco.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AtualizaMedicoDTO {
    @NotNull
    private Long id;
    private String nome;
    private String email;
    private AtualizaEnderecoDTO endereco;

}
