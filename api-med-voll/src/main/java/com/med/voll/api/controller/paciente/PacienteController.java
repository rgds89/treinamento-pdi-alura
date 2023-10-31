package com.med.voll.api.controller.paciente;

import com.med.voll.api.dto.paciente.AtualizaPacienteDTO;
import com.med.voll.api.dto.paciente.CadastraPacienteDTO;
import com.med.voll.api.dto.paciente.ListaPacienteDTO;
import com.med.voll.api.service.paciente.PacienteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.beans.Transient;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {
    private final PacienteService pacienteService;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@RequestBody @Valid CadastraPacienteDTO cadastraPacienteDTO){
        pacienteService.cadastrar(cadastraPacienteDTO);
    }

    @PutMapping
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public void atualizar (@RequestBody @Valid AtualizaPacienteDTO atualizaPacienteDTO){
        pacienteService.atualizar(atualizaPacienteDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public void deletar(@PathVariable Long id){
        pacienteService.deletar(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ListaPacienteDTO> listar(@PageableDefault(size = 10, sort = {"nome"})Pageable paginacao){
        return pacienteService.listar(paginacao);
    }
}
