package com.med.voll.api.dto.endereco;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CadastraEnderecoDTO {
    @NotBlank
    private String logradouro;
    @NotBlank
    private String bairro;
    private String numero;
    private String complemento;
    @NotNull
    @Digits(integer= 8, fraction = 0)
    private Long cep;
    @NotBlank
    private String cidade;
    @NotBlank
    private String uf;
}
