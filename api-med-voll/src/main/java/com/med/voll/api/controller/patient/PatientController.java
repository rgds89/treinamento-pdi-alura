package com.med.voll.api.controller.patient;

import com.med.voll.api.dto.patient.UpdatePatientDTO;
import com.med.voll.api.dto.patient.RegisterPatientDTO;
import com.med.voll.api.dto.patient.DetailPatientDTO;
import com.med.voll.api.dto.patient.ListPatientDTO;
import com.med.voll.api.service.patient.PatientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearer-key")
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    @Transactional
    public ResponseEntity<DetailPatientDTO> cadastrar(@RequestBody @Valid RegisterPatientDTO registerPatientDTO, UriComponentsBuilder uriComponentsBuilder){
        DetailPatientDTO detailPatientDTO = patientService.cadastrar(registerPatientDTO);
        URI uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(detailPatientDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(detailPatientDTO);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetailPatientDTO> atualizar (@RequestBody @Valid UpdatePatientDTO updatePatientDTO){
        DetailPatientDTO detailPatientDTO = patientService.atualizar(updatePatientDTO);
        return ResponseEntity.ok().body(detailPatientDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        patientService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailPatientDTO> findPaciente(@PathVariable Long id){
        return ResponseEntity.ok().body(patientService.findPaciente(id));
    }

    @GetMapping
    public ResponseEntity<Page<ListPatientDTO>> listar(@PageableDefault(size = 10, sort = {"nome"})Pageable paginacao){
        return ResponseEntity.ok().body(patientService.listar(paginacao));
    }
}
