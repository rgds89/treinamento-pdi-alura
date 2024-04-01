package br.com.alura.adopet.api.validacao;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.enums.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTutorComLimiteDeAdocoes implements ValidacaoSolicitacaoAdocao {
    @Autowired
    private AdocaoRepository adocaoRepository;

    public void validar(SolicitacaoAdocaoDto solicitacaoAdocaoDto) {

        if (adocaoRepository.countByTutorIdAndStatus(solicitacaoAdocaoDto.idTutor(), StatusAdocao.APROVADO).equals(5L)) {
            throw new ValidacaoException("Tutor chegou ao limite máximo de 5 adoções!");
        }
    }
}
