package com.med.voll.api.controller;

import com.med.voll.api.MedicoService;
import com.med.voll.api.dto.MedicoDTO;
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
    public void cadastrar(@RequestBody MedicoDTO medicoDTO){
        medicoService.cadastrar(medicoDTO);
    }
}
