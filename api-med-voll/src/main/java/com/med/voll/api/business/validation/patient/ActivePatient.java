package com.med.voll.api.business.validation.patient;

import com.med.voll.api.business.validation.ValidateScheduleConsulation;
import com.med.voll.api.dto.consulation.RegisterCosulationDTO;
import com.med.voll.api.repository.patient.PatientRepositoy;
import com.med.voll.api.service.consulation.exception.ValidationConsulationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActivePatient implements ValidateScheduleConsulation {
    private final PatientRepositoy patientRepositoy;
    public void validate(RegisterCosulationDTO registerCosulationDTO) {
        if (!patientRepositoy.isPatientActive(registerCosulationDTO.getIdPaciente())){
            throw new ValidationConsulationException("Paciente não está ativo.");
        }
    }
}
