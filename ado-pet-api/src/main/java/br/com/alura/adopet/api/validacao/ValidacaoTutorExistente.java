package br.com.alura.adopet.api.validacao;

import br.com.alura.adopet.api.dto.CadastraTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTutorExistente implements ValidacaoCadastraTutor{
    @Autowired
    private TutorRepository tutorRepository;

    public void validar(CadastraTutorDto dto) {
        if(tutorRepository.existsByEmailOrTelefone(dto.telefone(), dto.email()))
            throw new ValidacaoException("Dados já cadastrados para outro tutor!");

    }
}
