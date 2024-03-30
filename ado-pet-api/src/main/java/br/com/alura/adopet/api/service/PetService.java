package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.dto.CadastraPetDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;
    private final AbrigoRepository abrigoRepository;

    public void cadastrar(CadastraPetDto dto) {
        Abrigo abrigo;
        if (dto.abrigoId() != null) {
            abrigo = abrigoRepository.findById(dto.abrigoId()).orElseThrow(() -> new EntityNotFoundException("Abrigo não encontrado"));
        } else if (dto.abrigoNome() != null) {
            try {
                abrigo = abrigoRepository.findByNome(dto.abrigoNome());
            } catch (EntityNotFoundException e) {
                throw new EntityNotFoundException("Abrigo não encontrado");
            }
        } else {
            throw new ValidacaoException("Informe o id ou nome do abrigo!");
        }
        Pet pet = new Pet(dto.tipo(), dto.nome(), dto.raca(), dto.idade(), dto.cor(), dto.peso(), abrigo);
        abrigo.getPets().add(pet);
        petRepository.save(pet);
        abrigoRepository.save(abrigo);
    }

    public List<PetDto> listarTodosPetsDisponiveis() {
        List<Pet> pets = petRepository.findByAdotado(false);
        return buildListaPetDto(pets);
    }

    public List<PetDto> listarPets(Long abrigoId, String abrigoNome) {
        List<Pet> pets = petRepository.findByAbrigoIdOrNome(abrigoId, abrigoNome);
        return buildListaPetDto(pets);
    }

    private List<PetDto> buildListaPetDto(List<Pet> pets) {
        ArrayList<PetDto> petDtos = new ArrayList<>();
        pets.forEach(p -> {
            petDtos.add(PetDto.builder()
                    .id(p.getId())
                    .tipo(p.getTipo())
                    .nome(p.getNome())
                    .raca(p.getRaca())
                    .idade(p.getIdade())
                    .cor(p.getCor())
                    .peso(p.getPeso())
                    .adotado(p.getAdotado())
                    .abrigo(buildAbrigoDto(p.getAbrigo()))
                    .build());
        });
        return petDtos;
    }

    private AbrigoDto buildAbrigoDto(Abrigo abrigos) {
        return AbrigoDto.builder()
                .id(abrigos.getId())
                .nome(abrigos.getNome())
                .telefone(abrigos.getTelefone())
                .email(abrigos.getEmail())
                .build();
    }

}
