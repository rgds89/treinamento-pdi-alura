package com.med.voll.api.business.validation.consulation;

import com.med.voll.api.business.validation.ValidateScheduleConsulation;
import com.med.voll.api.dto.consulation.RegisterCosulationDTO;
import com.med.voll.api.service.consulation.exception.ValidationConsulationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ConsulationRequestSchedule implements ValidateScheduleConsulation {
    public void validate(RegisterCosulationDTO registerCosulationDTO) {
        var dateCosulation = registerCosulationDTO.getData();
        var dateNow = LocalDateTime.now();
        if (Duration.between(dateNow, dateCosulation).toMinutes() < 30){
            throw new ValidationConsulationException("Consulta deve ser agendada com no mínimo 30 min de antecedência.");
        }
    }
}
