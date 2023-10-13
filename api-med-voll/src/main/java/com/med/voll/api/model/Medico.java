package com.med.voll.api.model;

import com.med.voll.api.enums.Especialidade;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="medvoll_medico")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
@Builder
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private Long  crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @OneToOne
    private Endereco endereco;
}
