package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacao.ValidacaoSolicitacaoAdocao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AdocaoServiceTest {
    @Mock
    private SolicitacaoAdocaoDto solicitacaoAdocaoDto;
    @Mock
    private AdocaoRepository adocaoRepository;
    @Mock
    private PetRepository petRepository;
    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private EmailService emailService;
    @Mock
    private ValidacaoSolicitacaoAdocao validador1;
    @Mock
    private ValidacaoSolicitacaoAdocao validador2;
    @Spy
    private List<ValidacaoSolicitacaoAdocao> validacoes = new ArrayList<>();
    @Mock
    private Pet pet;
    @Mock
    private Tutor tutor;
    @Mock
    private Abrigo abrigo;
    @InjectMocks
    private AdocaoService adocaoService;
    @Captor
    private ArgumentCaptor<Adocao> adocaoArgumentCaptor;

    @Test
    void deveriaSalvarAdocaoAoSolicitar() {
        given(petRepository.findById(solicitacaoAdocaoDto.idPet())).willReturn(Optional.of(pet));
        given(tutorRepository.findById(solicitacaoAdocaoDto.idTutor())).willReturn(Optional.of(tutor));
        given(pet.getAbrigo()).willReturn(abrigo);
        adocaoService.solicitar(solicitacaoAdocaoDto);
        then(adocaoRepository).should().save(adocaoArgumentCaptor.capture());
        Adocao adocao = adocaoArgumentCaptor.getValue();
        assertEquals(pet, adocao.getPet());
        assertEquals(tutor, adocao.getTutor());
        assertEquals(solicitacaoAdocaoDto.motivo(), adocao.getMotivo());
    }

    @Test
    void deveriaChamarValidacoesAoSolicitar() {
        given(petRepository.findById(solicitacaoAdocaoDto.idPet())).willReturn(Optional.of(pet));
        given(tutorRepository.findById(solicitacaoAdocaoDto.idTutor())).willReturn(Optional.of(tutor));
        given(pet.getAbrigo()).willReturn(abrigo);
        validacoes.addAll(Arrays.asList(validador1, validador2));
        adocaoService.solicitar(solicitacaoAdocaoDto);
        then(validador1).should().validar(solicitacaoAdocaoDto);
        then(validador2).should().validar(solicitacaoAdocaoDto);
    }

}