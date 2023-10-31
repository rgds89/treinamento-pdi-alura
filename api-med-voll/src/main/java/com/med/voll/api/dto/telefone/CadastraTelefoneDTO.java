package com.med.voll.api.dto.telefone;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CadastraTelefoneDTO {
    @NotBlank
    private String ddd;

    @Digits(integer= 9, fraction = 0)
    @NotBlank
    private String numero;
}
