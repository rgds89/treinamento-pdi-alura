package com.med.voll.api.dto.telephone;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateTelephoneDTO {
    @NotNull
    private Long id;
    private String ddd;
    private String numero;
}
