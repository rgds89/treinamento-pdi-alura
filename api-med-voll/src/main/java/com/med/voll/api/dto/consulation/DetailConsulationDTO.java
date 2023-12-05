package com.med.voll.api.dto.consulation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class DetailConsulationDTO {
    private Long id;
    private Long idMedico;
    private Long idPaciente;
    private LocalDateTime data;
}
