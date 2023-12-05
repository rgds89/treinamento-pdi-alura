package com.med.voll.api.dto.consulation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class RegisterCosulationDTO {
    private Long idMedico;

    @NotNull
    private Long idPaciente;

    @NotNull
    @Future
    private LocalDateTime data;
}
