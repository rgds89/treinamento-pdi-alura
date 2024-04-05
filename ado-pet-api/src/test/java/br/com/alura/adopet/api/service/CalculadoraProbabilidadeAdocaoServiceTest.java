package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.TipoPet;
import br.com.alura.adopet.api.model.enums.ProbabilidadeAdocao;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = CalculadoraProbabilidadeAdocaoService.class)
class CalculadoraProbabilidadeAdocaoServiceTest {
    private Pet pet;

    @Test
    void deveRetornarProbabilidadeAltaParaPetComNotaMaiorOuIgualA8TipoPetCachorro() {
        CalculadoraProbabilidadeAdocaoService calculadoraProbabilidadeAdocaoService = new CalculadoraProbabilidadeAdocaoService();
        pet = new Pet();
        pet.setTipo(TipoPet.CACHORRO);
        pet.setPeso(4.0F);
        pet.setIdade(4);

        assertEquals(ProbabilidadeAdocao.ALTA, calculadoraProbabilidadeAdocaoService.calcularProbabilidadeAdocao(pet));
    }

    @Test
    void deveRetornarProbabilidadeMediaParaPetComNotaMaiorOuIgualA5EMenorQue8TipoPetCachorro() {
        CalculadoraProbabilidadeAdocaoService calculadoraProbabilidadeAdocaoService = new CalculadoraProbabilidadeAdocaoService();
        pet = new Pet();
        pet.setTipo(TipoPet.CACHORRO);
        pet.setPeso(4.0F);
        pet.setIdade(15);

        assertEquals(ProbabilidadeAdocao.MEDIA, calculadoraProbabilidadeAdocaoService.calcularProbabilidadeAdocao(pet));
    }

    @Test
    void deveRetornarProbabilidadeBaixaParaPetComNotaMenorQue5TipoPetCachorro() {
        CalculadoraProbabilidadeAdocaoService calculadoraProbabilidadeAdocaoService = new CalculadoraProbabilidadeAdocaoService();
        pet = new Pet();
        pet.setTipo(TipoPet.CACHORRO);
        pet.setPeso(20.0F);
        pet.setIdade(16);

        assertEquals(ProbabilidadeAdocao.BAIXA, calculadoraProbabilidadeAdocaoService.calcularProbabilidadeAdocao(pet));
    }

    @Test
    void deveRetornarProbabilidadeAltaParaPetComNotaMaiorOuIgualA8TipoPetGato() {
        CalculadoraProbabilidadeAdocaoService calculadoraProbabilidadeAdocaoService = new CalculadoraProbabilidadeAdocaoService();
        pet = new Pet();
        pet.setTipo(TipoPet.GATO);
        pet.setPeso(4.0F);
        pet.setIdade(4);

        assertEquals(ProbabilidadeAdocao.ALTA, calculadoraProbabilidadeAdocaoService.calcularProbabilidadeAdocao(pet));
    }

    @Test
    void deveRetornarProbabilidadeMediaParaPetComNotaMaiorOuIgualA5EMenorQue8TipoPetGato() {
        CalculadoraProbabilidadeAdocaoService calculadoraProbabilidadeAdocaoService = new CalculadoraProbabilidadeAdocaoService();
        pet = new Pet();
        pet.setTipo(TipoPet.GATO);
        pet.setPeso(4.0F);
        pet.setIdade(15);

        assertEquals(ProbabilidadeAdocao.MEDIA, calculadoraProbabilidadeAdocaoService.calcularProbabilidadeAdocao(pet));
    }

    @Test
    void deveRetornarProbabilidadeBaixaParaPetComNotaMenorQue5TipoPetGato() {
        CalculadoraProbabilidadeAdocaoService calculadoraProbabilidadeAdocaoService = new CalculadoraProbabilidadeAdocaoService();
        pet = new Pet();
        pet.setTipo(TipoPet.GATO);
        pet.setPeso(20.0F);
        pet.setIdade(16);

        assertEquals(ProbabilidadeAdocao.BAIXA, calculadoraProbabilidadeAdocaoService.calcularProbabilidadeAdocao(pet));
    }

}