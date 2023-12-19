package com.med.voll.api.controller.consulation;

import com.med.voll.api.dto.consulation.DetailConsulationDTO;
import com.med.voll.api.dto.consulation.RegisterCosulationDTO;
import com.med.voll.api.service.consulation.ConsulationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class ConsulationController {
    private final ConsulationService consulationService;

    @PostMapping
    public ResponseEntity<?> createConsulation(@RequestBody @Valid RegisterCosulationDTO registerCosulationDTO) {
        consulationService.createConsulation(registerCosulationDTO);
        return ResponseEntity.ok().build();
    }
}
