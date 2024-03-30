package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizarTutorDto;
import br.com.alura.adopet.api.dto.CadastraTutorDto;
import br.com.alura.adopet.api.dto.TutorDto;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacao.ValidacaoCadastraTutor;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TutorService {
    private final ValidacaoCadastraTutor validacaoTutor;
    private final TutorRepository tutorRepository;

    public void cadastrar(CadastraTutorDto dto) {
        validacaoTutor.validar(dto);
        buildTutor(dto);
    }

    private void buildTutor(CadastraTutorDto dto) {
        Tutor tutor = new Tutor(dto.nome(), dto.telefone(), dto.email());
        tutorRepository.save(tutor);
    }

    public TutorDto atualizar(AtualizarTutorDto dto) {
        Tutor tutor = tutorRepository.findById(dto.id()).orElseThrow(() -> new EntityNotFoundException("Tutor não encontrado"));
        tutor.atualizar(dto);
        return buildTutorDto(tutor);
    }

    private TutorDto buildTutorDto(Tutor tutor) {
        return TutorDto.builder()
                .id(tutor.getId())
                .nome(tutor.getNome())
                .email(tutor.getEmail())
                .telefone(tutor.getTelefone())
                .build();
    }


}
