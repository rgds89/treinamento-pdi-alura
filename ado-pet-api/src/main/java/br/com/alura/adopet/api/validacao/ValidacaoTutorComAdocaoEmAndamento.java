package br.com.alura.adopet.api.validacao;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidacaoTutorComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao{
    @Autowired
    private AdocaoRepository adocaoRepository;

    public void validar(SolicitacaoAdocaoDto solicitacaoAdocaoDto) {
        List<Adocao> adocoes = adocaoRepository.findAll();
        adocoes.stream().filter(a -> a.getTutor().getId().equals(solicitacaoAdocaoDto.idTutor()) &&
                a.getStatus().equals(StatusAdocao.AGUARDANDO_AVALIACAO)).findFirst().ifPresent(a -> {
            throw new ValidacaoException("Tutor já possui outra adoção aguardando avaliação!");
        });
    }
}
