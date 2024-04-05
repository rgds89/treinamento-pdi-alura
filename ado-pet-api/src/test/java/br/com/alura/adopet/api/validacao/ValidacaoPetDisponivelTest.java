package br.com.alura.adopet.api.validacao;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = ValidacaoPetDisponivel.class)
public class ValidacaoPetDisponivelTest {
    @Mock
    private PetRepository petRepository;
    @InjectMocks
    ValidacaoPetDisponivel validacao;
    @Mock
    private Pet pet;
    @Mock
    private SolicitacaoAdocaoDto dto;

    @Test
    void deveriaPermitirSolicitarAdocaoDePetDisponivel() {
        BDDMockito.given(petRepository.findById(dto.idPet())).willReturn(java.util.Optional.of(pet));
        BDDMockito.given(pet.getAdotado()).willReturn(false);
        Assertions.assertDoesNotThrow(() -> validacao.validar(dto));
    }

    @Test
    void naoDeveriaPermitirSolicitarAdocaoQuandoPetJaFoiAdotado() {
        BDDMockito.given(petRepository.findById(dto.idPet())).willReturn(java.util.Optional.of(pet));
        BDDMockito.given(pet.getAdotado()).willReturn(true);
        Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(dto));
    }
}
