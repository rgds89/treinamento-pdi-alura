package com.med.voll.api.model.telephone;

import com.med.voll.api.model.doctor.Doctor;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "medvoll_telefone")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
@Builder
public class Telephone implements Serializable {
    private static final long serialVersionUID = -8683875028732445726L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ddd;
    private String numero;
    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Doctor doctor;
}
