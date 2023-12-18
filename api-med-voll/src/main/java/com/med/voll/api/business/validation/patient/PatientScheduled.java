package com.med.voll.api.business.validation.patient;

import com.med.voll.api.business.validation.ValidateScheduleConsulation;
import com.med.voll.api.dto.consulation.RegisterCosulationDTO;
import com.med.voll.api.repository.consulation.ConsulationRepository;
import com.med.voll.api.service.consulation.exception.ValidationConsulationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PatientScheduled implements ValidateScheduleConsulation {
    private final ConsulationRepository consulationRepository;

    public void validate(RegisterCosulationDTO registerCosulationDTO) {
        if (consulationRepository.existsByPatientIdAndDataBetween(registerCosulationDTO.getIdPaciente(), registerCosulationDTO.getData().withHour(7),
                registerCosulationDTO.getData().withHour(18))) {
            throw new ValidationConsulationException("Paciente já possui consulta agendada para a data informada.");
        }
    }
}
