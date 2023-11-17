package com.med.voll.api.dto.telephone;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterTelephoneDTO {
    @NotBlank
    private String ddd;

    @Digits(integer= 9, fraction = 0)
    @NotBlank
    private String numero;
}
