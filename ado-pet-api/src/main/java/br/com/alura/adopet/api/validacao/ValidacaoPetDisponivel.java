package br.com.alura.adopet.api.validacao;

import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPetDisponivel {
    @Autowired
    private PetRepository petRepository;

    public void validar(Long idPet) {
        Pet pet = petRepository.findById(idPet).orElseThrow(() -> new ValidacaoException("Pet não encontrado!"));
        if (pet.getAdotado()) {
            throw new ValidacaoException("Pet já foi adotado!");
        }
    }
}
