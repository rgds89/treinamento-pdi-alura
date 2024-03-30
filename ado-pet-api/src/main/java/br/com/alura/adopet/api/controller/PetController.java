package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.CadastraPetDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.service.PetService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetRepository repository;

    @Autowired
    private PetService petService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastraPetDto pet) {
        petService.cadastrar(pet);
        return ResponseEntity.ok("Pet cadastrado com sucesso!");
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<PetDto>> listarTodosDisponiveis() {
        return ResponseEntity.ok(petService.listarTodosPetsDisponiveis());
    }

    @GetMapping("/{idOuNome}")
    public ResponseEntity<List<PetDto>> listarPets(@PathVariable String idOuNome) {
        if (idOuNome.matches("[0-9]+")) {
            Long id = Long.parseLong(idOuNome);
            return ResponseEntity.ok(petService.listarPets(id, null));
        } else {
            return ResponseEntity.ok(petService.listarPets(null, idOuNome));
        }
    }
}
