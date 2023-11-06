package com.med.voll.api.controller.paciente;

import com.med.voll.api.dto.paciente.AtualizaPacienteDTO;
import com.med.voll.api.dto.paciente.CadastraPacienteDTO;
import com.med.voll.api.dto.paciente.DetalhamentoPacienteDTO;
import com.med.voll.api.dto.paciente.ListaPacienteDTO;
import com.med.voll.api.service.paciente.PacienteService;
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
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {
    private final PacienteService pacienteService;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhamentoPacienteDTO> cadastrar(@RequestBody @Valid CadastraPacienteDTO cadastraPacienteDTO, UriComponentsBuilder uriComponentsBuilder){
        DetalhamentoPacienteDTO detalhamentoPacienteDTO = pacienteService.cadastrar(cadastraPacienteDTO);
        URI uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(detalhamentoPacienteDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(detalhamentoPacienteDTO);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhamentoPacienteDTO> atualizar (@RequestBody @Valid AtualizaPacienteDTO atualizaPacienteDTO){
        DetalhamentoPacienteDTO detalhamentoPacienteDTO = pacienteService.atualizar(atualizaPacienteDTO);
        return ResponseEntity.ok().body(detalhamentoPacienteDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        pacienteService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoPacienteDTO> findPaciente(@PathVariable Long id){
        return ResponseEntity.ok().body(pacienteService.findPaciente(id));
    }

    @GetMapping
    public ResponseEntity<Page<ListaPacienteDTO>> listar(@PageableDefault(size = 10, sort = {"nome"})Pageable paginacao){
        return ResponseEntity.ok().body(pacienteService.listar(paginacao));
    }
}
