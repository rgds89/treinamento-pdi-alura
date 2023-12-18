package com.med.voll.api.business.validation;

import com.med.voll.api.dto.consulation.RegisterCosulationDTO;
import org.springframework.stereotype.Component;

@Component
public interface ValidateScheduleConsulation {
    void validate(RegisterCosulationDTO registerCosulationDTO);
}
