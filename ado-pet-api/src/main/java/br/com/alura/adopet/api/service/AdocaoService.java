package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.ReprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacao.ValidacaoSolicitacaoAdocao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdocaoService {
    private final AdocaoRepository adocaoRepository;
    private final PetRepository petRepository;
    private final TutorRepository tutorRepository;
    private final EmailService emailService;
    private final List<ValidacaoSolicitacaoAdocao> validacoes;

    public void solicitar(SolicitacaoAdocaoDto solicitacaoAdocaoDto) {
        Pet pet = petRepository.findById(solicitacaoAdocaoDto.idPet()).orElseThrow(() -> new ValidacaoException("Pet não encontrado!"));
        Tutor tutor = tutorRepository.findById(solicitacaoAdocaoDto.idTutor()).orElseThrow(() -> new ValidacaoException("Tutor não encontrado!"));
        validacoes.forEach(v -> v.validar(solicitacaoAdocaoDto));

        Adocao adocao = buildAdocao(solicitacaoAdocaoDto, pet, tutor);


        emailService.enviar(adocao.getPet().getAbrigo().getEmail(),
                "Solicitação de adoção",
                "Olá " + adocao.getPet().getAbrigo().getNome() +
                        "!\n\nUma solicitação de adoção foi registrada hoje para o pet: " +
                        adocao.getPet().getNome() + ". \nFavor avaliar para aprovação ou reprovação.");
    }

    private Adocao buildAdocao(SolicitacaoAdocaoDto solicitacaoAdocaoDto, Pet pet, Tutor tutor) {
        Adocao adocao = new Adocao(tutor, pet, solicitacaoAdocaoDto.motivo());
        adocaoRepository.save(adocao);
        return adocao;
    }

    public void aprovar(AprovacaoAdocaoDto aprovarAdocaoDto) {
        Adocao adocao = adocaoRepository.findById(aprovarAdocaoDto.idAdocao()).orElseThrow(() -> new ValidacaoException("Adoção não encontrada!"));
        adocao.aprovar();
        adocaoRepository.save(adocao);
        emailService.enviar(adocao.getTutor().getEmail(),
                "Adoção aprovada",
                "Parabéns " + adocao.getTutor().getNome() +
                        "!\n\nSua adoção do pet " + adocao.getPet().getNome() +
                        ", solicitada em " + adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +
                        ", foi aprovada.\nFavor entrar em contato com o abrigo " + adocao.getPet().getAbrigo().getNome() + " para agendar a busca do seu pet.");
    }

    public void reprovar(ReprovacaoAdocaoDto reprovacaoAdocaoDto) {
        Adocao adocao = adocaoRepository.findById(reprovacaoAdocaoDto.idAdocao()).orElseThrow(() -> new ValidacaoException("Adoção não encontrada!"));
        adocao.reprovar(reprovacaoAdocaoDto.justificativa());
        adocaoRepository.save(adocao);
        emailService.enviar(adocao.getTutor().getEmail(),
                "Adoção reprovada",
                "Olá " + adocao.getTutor().getNome() +
                        "!\n\nInfelizmente sua adoção do pet " + adocao.getPet().getNome() +
                        ", solicitada em " + adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +
                        ", foi reprovada pelo abrigo " + adocao.getPet().getAbrigo().getNome() +
                        " com a seguinte justificativa: " + adocao.getJustificativaStatus());
    }
}
