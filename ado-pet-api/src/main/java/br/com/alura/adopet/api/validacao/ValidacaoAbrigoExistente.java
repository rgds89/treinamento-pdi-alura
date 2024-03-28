package br.com.alura.adopet.api.validacao;

import br.com.alura.adopet.api.dto.CadastraAbrigoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoAbrigoExistente implements  ValidacaoCadastraAbrigo {
    @Autowired
    private AbrigoRepository abrigoRepository;
    public void validar(CadastraAbrigoDto dto) {
        if(abrigoRepository.existsByNomeOrTelefoneOrEmail(dto.nome(), dto.telefone(), dto.email()))
            throw new ValidacaoException("Dados já cadastrados para outro abrigo!");
    }
}
