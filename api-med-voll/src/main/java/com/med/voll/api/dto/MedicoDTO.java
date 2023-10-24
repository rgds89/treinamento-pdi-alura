package com.med.voll.api.dto;

import com.med.voll.api.enums.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class MedicoDTO {
    @NotBlank
    private String nome;
    @NotBlank
    @Email
    private String email;
    @NotNull
    @Digits(integer= 6, fraction = 0)
    private Long  crm;
    @NotNull
    private Especialidade especialidade;
    @NotNull
    @Valid
    private EnderecoDTO endereco;
    @NotNull
    @Valid
    private List<TelefoneDTO> telefones;
}
