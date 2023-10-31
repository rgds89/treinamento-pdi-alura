package com.med.voll.api.model.medico;

import com.med.voll.api.enums.Especialidade;
import com.med.voll.api.model.endereco.Endereco;
import com.med.voll.api.model.telefone.Telefone;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name ="medvoll_medico")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
@Builder
public class Medico implements Serializable {
    private static final long serialVersionUID = 1373306994731249219L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private Long  crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @OneToOne
    @JoinColumn(name="id_endereco")
    private Endereco endereco;
    @OneToMany
    @JoinColumn(name = "id_medico")
    private List<Telefone> telefones;
    @Builder.Default
    private Boolean ativo = true;
}
