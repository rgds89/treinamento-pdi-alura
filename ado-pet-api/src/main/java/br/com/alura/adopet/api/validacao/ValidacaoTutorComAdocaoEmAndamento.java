package br.com.alura.adopet.api.validacao;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTutorComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {
    @Autowired
    private AdocaoRepository adocaoRepository;

    public void validar(SolicitacaoAdocaoDto solicitacaoAdocaoDto) {
        if (adocaoRepository.existsByTutorIdAndStatus(solicitacaoAdocaoDto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO)) {
            throw new ValidacaoException("Tutor já possui outra adoção aguardando avaliação!");
        }
    }
}
