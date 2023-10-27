package com.med.voll.api.dto;

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
    @Valid
    private AtualizaTelefoneDTO telefone;

}
