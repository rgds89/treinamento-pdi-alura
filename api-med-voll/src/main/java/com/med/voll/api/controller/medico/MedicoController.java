package com.med.voll.api.controller.medico;


import com.med.voll.api.dto.medico.AtualizaMedicoDTO;
import com.med.voll.api.dto.medico.CadastraMedicoDTO;
import com.med.voll.api.dto.medico.DetalhamentoMedicoDTO;
import com.med.voll.api.dto.medico.ListaMedicoDTO;
import com.med.voll.api.service.medico.MedicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/medicos")
@RequiredArgsConstructor
public class MedicoController {
    private final MedicoService medicoService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastraMedicoDTO cadastraMedicoDTO, UriComponentsBuilder uriComponentsBuilder) {
        DetalhamentoMedicoDTO detalhamentoMedicoDTO = medicoService.cadastrar(cadastraMedicoDTO);
        URI uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(detalhamentoMedicoDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(detalhamentoMedicoDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ListaMedicoDTO>> findMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok().body(medicoService.findMedicos(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoMedicoDTO> findMedico(@PathVariable Long id){
        return ResponseEntity.ok().body(medicoService.findMedico(id));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhamentoMedicoDTO> atualizar(@RequestBody @Valid AtualizaMedicoDTO atualizaMedicoDTO) {
        return ResponseEntity.ok().body(medicoService.atualizar(atualizaMedicoDTO));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        medicoService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
