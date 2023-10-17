package com.med.voll.api.controller;

import com.med.voll.api.MedicoService;
import com.med.voll.api.dto.MedicoDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
@RequiredArgsConstructor
public class MedicoController {
    private final MedicoService medicoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void cadastrar(@RequestBody @Valid MedicoDTO medicoDTO){
        medicoService.cadastrar(medicoDTO);
    }
}
