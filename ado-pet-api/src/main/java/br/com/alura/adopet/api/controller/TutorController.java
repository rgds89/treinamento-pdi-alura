package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AtualizarTutorDto;
import br.com.alura.adopet.api.dto.CadastraTutorDto;
import br.com.alura.adopet.api.dto.TutorDto;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {
    @Autowired
    private TutorService tutorService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastraTutorDto dto) {
        tutorService.cadastrar(dto);
        return ResponseEntity.ok("Tutor cadastrado com sucesso!");
    }

    @PutMapping
    @Transactional
    public ResponseEntity<TutorDto> atualizar(@RequestBody @Valid AtualizarTutorDto dto) {
        return ResponseEntity.ok().body(tutorService.atualizar(dto));
    }

}
