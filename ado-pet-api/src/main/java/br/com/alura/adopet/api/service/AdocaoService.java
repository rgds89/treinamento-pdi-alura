package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.ReprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdocaoService {
    private final AdocaoRepository adocaoRepository;
    private final PetRepository petRepository;
    private final TutorRepository tutorRepository;
    private final EmailService emailService;

    public void solicitar(SolicitacaoAdocaoDto solicitacaoAdocaoDto) {
        Pet pet = petRepository.findById(solicitacaoAdocaoDto.idPet()).orElseThrow(() -> new ValidacaoException("Pet não encontrado!"));
        Tutor tutor = tutorRepository.findById(solicitacaoAdocaoDto.idTutor()).orElseThrow(() -> new ValidacaoException("Tutor não encontrado!"));
        if (pet.getAdotado()) {
            throw new ValidacaoException("Pet já foi adotado!");
        } else {
            List<Adocao> adocoes = adocaoRepository.findAll();
            for (Adocao a : adocoes) {
                if (a.getTutor() == tutor && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
                    throw new ValidacaoException("Tutor já possui outra adoção aguardando avaliação!");
                }
            }
            for (Adocao a : adocoes) {
                if (a.getPet() == pet && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
                    throw new ValidacaoException("Pet já está aguardando avaliação para ser adotado!");
                }
            }
            for (Adocao a : adocoes) {
                int contador = 0;
                if (a.getTutor() == tutor && a.getStatus() == StatusAdocao.APROVADO) {
                    contador = contador + 1;
                }
                if (contador == 5) {
                    throw new ValidacaoException("Tutor chegou ao limite máximo de 5 adoções!");
                }
            }
        }

        Adocao adocao = new Adocao();
        adocao.setData(LocalDateTime.now());
        adocao.setStatus(StatusAdocao.AGUARDANDO_AVALIACAO);
        adocao.setPet(pet);
        adocao.setTutor(tutor);
        adocao.setMotivo(solicitacaoAdocaoDto.motivo());
        adocaoRepository.save(adocao);
        emailService.enviar(adocao.getPet().getAbrigo().getEmail(),
                "Solicitação de adoção",
                "Olá " + adocao.getPet().getAbrigo().getNome() +
                        "!\n\nUma solicitação de adoção foi registrada hoje para o pet: " +
                        adocao.getPet().getNome() + ". \nFavor avaliar para aprovação ou reprovação.");
    }

    public void aprovar(AprovacaoAdocaoDto aprovarAdocaoDto) {
        Adocao adocao = adocaoRepository.findById(aprovarAdocaoDto.idAdocao()).orElseThrow(() -> new ValidacaoException("Adoção não encontrada!"));
        adocao.setStatus(StatusAdocao.APROVADO);
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
        adocao.setStatus(StatusAdocao.REPROVADO);
        adocao.setJustificativaStatus(reprovacaoAdocaoDto.justificativa());
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
