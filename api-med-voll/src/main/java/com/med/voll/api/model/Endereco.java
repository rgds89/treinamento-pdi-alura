package com.med.voll.api.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="medvoll_endereco")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
@Builder
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String logradouro;
    private String bairro;
    private String numero;
    private String complemento;
    private Long cep;
    private String cidade;
    private String uf;
}
