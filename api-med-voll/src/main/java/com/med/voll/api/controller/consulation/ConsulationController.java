package com.med.voll.api.controller.consulation;

import com.med.voll.api.dto.consulation.DetailConsulationDTO;
import com.med.voll.api.dto.consulation.RegisterCosulationDTO;
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
public class ConsulationController {

    @PostMapping
    public ResponseEntity<?> createConsulation(@RequestBody @Valid RegisterCosulationDTO registerCosulationDTO) {
        return ResponseEntity.ok().body(new DetailConsulationDTO(null, null, null, null));
    }
}
