package com.med.voll.api.dto;

import com.med.voll.api.enums.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MedicoDTO {
    @NotBlank
    private String nome;
    @NotBlank
    @Email
    private String email;
    @NotNull
    @Min(4)
    @Max(6)
    private Long  crm;
    @NotNull
    private Especialidade especialidade;
    @NotNull
    @Valid
    private EnderecoDTO endereco;
}
