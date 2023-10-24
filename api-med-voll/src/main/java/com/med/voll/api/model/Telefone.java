package com.med.voll.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medvoll_telefone")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
@Builder
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ddd;
    private String numero;
    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;
}
