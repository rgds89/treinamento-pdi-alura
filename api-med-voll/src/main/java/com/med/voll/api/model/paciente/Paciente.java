package com.med.voll.api.model.paciente;

import com.med.voll.api.model.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name ="medvoll_paciente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
@Builder
public class Paciente implements Serializable {
    private static final long serialVersionUID = -1935127663430642310L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    @Builder.Default
    private Boolean ativo = true;
    @OneToOne
    @JoinColumn(name="id_endereco")
    private Endereco endereco;
}
