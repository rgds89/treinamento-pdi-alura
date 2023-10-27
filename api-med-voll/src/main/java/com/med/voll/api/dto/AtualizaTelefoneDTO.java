package com.med.voll.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AtualizaTelefoneDTO {
    @NotNull
    private Long id;
    private String ddd;
    private String numero;
}
