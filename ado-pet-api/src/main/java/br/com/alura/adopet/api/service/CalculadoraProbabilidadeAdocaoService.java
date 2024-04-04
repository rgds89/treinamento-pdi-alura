package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.TipoPet;
import br.com.alura.adopet.api.model.enums.ProbabilidadeAdocao;
import org.springframework.stereotype.Service;

@Service
public class CalculadoraProbabilidadeAdocaoService {
    public ProbabilidadeAdocao calcularProbabilidadeAdocao(Pet pet) {
        int nota = calcularNota(pet);
        return nota >= 8 ? ProbabilidadeAdocao.ALTA : nota >= 5 ? ProbabilidadeAdocao.MEDIA : ProbabilidadeAdocao.BAIXA;
    }

    private int calcularNota(Pet pet) {
        int nota = 10;
        nota = TipoPet.CACHORRO.equals(pet.getTipo()) && pet.getPeso().intValue() > 15 ? nota - 2 : TipoPet.GATO.equals(pet.getTipo()) && pet.getPeso().intValue() > 10 ? nota - 2 : nota;
        nota = pet.getIdade() >= 15 ? nota - 5 : pet.getIdade() >= 10 ? nota - 4 : nota;

        return nota;
    }
}
