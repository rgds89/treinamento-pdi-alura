package com.med.voll.api.business.validation.clinic;

import com.med.voll.api.business.validation.ValidateScheduleConsulation;
import com.med.voll.api.dto.consulation.RegisterCosulationDTO;
import com.med.voll.api.service.consulation.exception.ValidationConsulationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ClinicHoursOfOperation implements ValidateScheduleConsulation {
    public void validate(RegisterCosulationDTO registerCosulationDTO) {
        var dateCosulation = registerCosulationDTO.getData();
        if (registerCosulationDTO.getData().getHour() < 7 || registerCosulationDTO.getData().getHour() > 18 ||
                dateCosulation.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            throw new ValidationConsulationException("Consulta fora do horário de funcionamento da clínica.");
        }
    }
}
