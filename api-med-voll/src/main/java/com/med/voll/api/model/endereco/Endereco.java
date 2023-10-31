package com.med.voll.api.model.endereco;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="medvoll_endereco")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
@Builder
public class Endereco implements Serializable {
    private static final long serialVersionUID = 5249882090546893129L;

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
