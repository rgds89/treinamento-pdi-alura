package com.med.voll.api.controller.doctor;


import com.med.voll.api.dto.doctor.UpdateDoctorDTO;
import com.med.voll.api.dto.doctor.RegisterDoctorDTO;
import com.med.voll.api.dto.doctor.DetailDoctorDTO;
import com.med.voll.api.dto.doctor.ListaMedicoDTO;
import com.med.voll.api.service.doctor.DoctorService;
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
@RequestMapping("/medicos")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid RegisterDoctorDTO registerDoctorDTO, UriComponentsBuilder uriComponentsBuilder) {
        DetailDoctorDTO detailDoctorDTO = doctorService.cadastrar(registerDoctorDTO);
        URI uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(detailDoctorDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(detailDoctorDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ListaMedicoDTO>> findMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok().body(doctorService.findMedicos(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailDoctorDTO> findMedico(@PathVariable Long id){
        return ResponseEntity.ok().body(doctorService.findMedico(id));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetailDoctorDTO> atualizar(@RequestBody @Valid UpdateDoctorDTO updateDoctorDTO) {
        return ResponseEntity.ok().body(doctorService.atualizar(updateDoctorDTO));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        doctorService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
