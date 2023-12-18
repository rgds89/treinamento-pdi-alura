package com.med.voll.api.business.validation.doctor;

import com.med.voll.api.business.validation.ValidateScheduleConsulation;
import com.med.voll.api.dto.consulation.RegisterCosulationDTO;
import com.med.voll.api.repository.doctor.DoctorRepository;
import com.med.voll.api.service.consulation.exception.ValidationConsulationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DoctorAvailable implements ValidateScheduleConsulation {
    private final DoctorRepository doctorRepository;
    public void validate(RegisterCosulationDTO registerCosulationDTO) {
        if (!doctorRepository.isDoctorAvailable(registerCosulationDTO.getIdMedico(), registerCosulationDTO.getData())) {
            throw new ValidationConsulationException("Médico não disponível para a data informada.");
        }
    }
}
