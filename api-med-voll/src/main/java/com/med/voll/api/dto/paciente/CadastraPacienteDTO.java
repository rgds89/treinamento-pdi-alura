package com.med.voll.api.dto.paciente;

import com.med.voll.api.dto.endereco.CadastraEnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CadastraPacienteDTO {
    @NotNull
    private String nome;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
    private String cpf;
    @NotBlank
    private String telefone;
    @Valid
    @NotNull
    private CadastraEnderecoDTO endereco;
}
