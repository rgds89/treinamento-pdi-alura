package com.med.voll.api.controller;

import com.med.voll.api.dto.AtualizaMedicoDTO;
import com.med.voll.api.dto.ListaMedicoDTO;
import com.med.voll.api.dto.CadastraMedicoDTO;
import com.med.voll.api.service.MedicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public void cadastrar(@RequestBody @Valid CadastraMedicoDTO cadastraMedicoDTO){
        medicoService.cadastrar(cadastraMedicoDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ListaMedicoDTO> findMedicos(@PageableDefault(size = 10, sort = {"nome"})  Pageable paginacao){
        return medicoService.findMedicos(paginacao);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public AtualizaMedicoDTO atualizar(@RequestBody @Valid  AtualizaMedicoDTO atualizaMedicoDTO){
        return medicoService.atualizar(atualizaMedicoDTO);
    }
}
