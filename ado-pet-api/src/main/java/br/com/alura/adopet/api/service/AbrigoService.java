package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.dto.CadastraAbrigoDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.validacao.ValidacaoCadastraAbrigo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AbrigoService {
    private final ValidacaoCadastraAbrigo validacaoCadastraAbrigo;
    private final AbrigoRepository abrigoRepository;
    public void cadastrar(CadastraAbrigoDto dto) {
        validacaoCadastraAbrigo.validar(dto);
        buildAbrigo(dto);
    }

    public List<AbrigoDto> listar() {
        return buildListaAbrigoDto(abrigoRepository.findAll());
    }

    public AbrigoDto getAbrigoPorIdOuNome(Long id, String nome) {
        Abrigo abrigo = abrigoRepository.findByIdOrNome(id, nome);
        return buildAbrigoDto(abrigo);
    }

    private AbrigoDto buildAbrigoDto(Abrigo abrigo) {
        return AbrigoDto.builder()
                .id(abrigo.getId())
                .nome(abrigo.getNome())
                .telefone(abrigo.getTelefone())
                .email(abrigo.getEmail())
                .pets(buildListaPetDto(abrigo.getPets()))
                .build();
    }

    private List<AbrigoDto> buildListaAbrigoDto(List<Abrigo> abrigos) {
        ArrayList<AbrigoDto> abrigoDtos = new ArrayList<>();
        abrigos.forEach(a -> {
            abrigoDtos.add(AbrigoDto.builder()
                    .id(a.getId())
                    .nome(a.getNome())
                    .telefone(a.getTelefone())
                    .email(a.getEmail())
                    .pets(buildListaPetDto(a.getPets()))
                    .build());
        });
        return abrigoDtos;
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
                    .build());
        });
        return petDtos;
    }

    private void buildAbrigo(CadastraAbrigoDto dto) {
        Abrigo abrigo = new Abrigo(dto.nome(), dto.telefone(), dto.email());
        abrigoRepository.save(abrigo);
    }
}
