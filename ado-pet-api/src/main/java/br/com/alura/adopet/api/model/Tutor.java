package br.com.alura.adopet.api.model;

import br.com.alura.adopet.api.dto.AtualizarTutorDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tutores")
@NoArgsConstructor
@Data
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    @OneToMany(mappedBy = "tutor")
    private List<Adocao> adocoes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tutor tutor = (Tutor) o;
        return Objects.equals(id, tutor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Tutor(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public void atualizar(AtualizarTutorDto dto) {
        this.nome = dto.nome() != null ? dto.nome() : this.nome;
        this.telefone = dto.telefone() != null ? dto.telefone() : this.telefone;
        this.email = dto.email() != null ? dto.email() : this.email;
    }
}
