package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.dto.CadastraAbrigoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.service.AbrigoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {
    @Autowired
    private AbrigoService abrigoService;

    @GetMapping
    public ResponseEntity<List<AbrigoDto>> listar() {
        return ResponseEntity.ok(abrigoService.listar());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastraAbrigoDto dto) {
        abrigoService.cadastrar(dto);
        return ResponseEntity.ok("Abrigo cadastrado com sucesso!");
    }

    @GetMapping("/{idOuNome}")
    public ResponseEntity<AbrigoDto> getAbrigoPorIdOuNome(@PathVariable String idOuNome) {
        if (idOuNome.matches("[0-9]+")) {
            Long id = Long.parseLong(idOuNome);
            return ResponseEntity.ok(abrigoService.getAbrigoPorIdOuNome(id, null));
        } else {
            return ResponseEntity.ok(abrigoService.getAbrigoPorIdOuNome(null, idOuNome));
        }
    }
}
