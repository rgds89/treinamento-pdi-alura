package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.TipoPet;
import br.com.alura.adopet.api.model.enums.ProbabilidadeAdocao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraProbabilidadeAdocaoServiceTest {
    private Pet pet;

    @Test
    void deveRetornarProbabilidadeAltaParaPetComNotaMaiorOuIgualA8() {
        CalculadoraProbabilidadeAdocaoService calculadoraProbabilidadeAdocaoService = new CalculadoraProbabilidadeAdocaoService();
        pet = new Pet();
        pet.setTipo(TipoPet.CACHORRO);
        pet.setPeso(4.0F);
        pet.setIdade(4);

        assertEquals(ProbabilidadeAdocao.ALTA, calculadoraProbabilidadeAdocaoService.calcularProbabilidadeAdocao(pet));
    }

}