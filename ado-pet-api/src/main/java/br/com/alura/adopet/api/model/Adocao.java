package br.com.alura.adopet.api.model;

import br.com.alura.adopet.api.model.enums.StatusAdocao;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "adocoes")
@NoArgsConstructor
@Data
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime data;
    @ManyToOne(fetch = FetchType.LAZY)
    private Tutor tutor;
    @OneToOne(fetch = FetchType.LAZY)
    private Pet pet;
    private String motivo;
    @Enumerated(EnumType.STRING)
    private StatusAdocao status;
    private String justificativaStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adocao adocao = (Adocao) o;
        return Objects.equals(id, adocao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Adocao(Tutor tutor, Pet pet, String motivo) {
        this.data = LocalDateTime.now();
        this.tutor = tutor;
        this.pet = pet;
        this.motivo = motivo;
        this.status = StatusAdocao.AGUARDANDO_AVALIACAO;
    }

    public void aprovar() {
        this.status = StatusAdocao.APROVADO;
    }

    public void reprovar(String justificativa) {
        this.status = StatusAdocao.REPROVADO;
        this.justificativaStatus = justificativa;
    }
}
