package com.med.voll.api.model.consulation;

import com.med.voll.api.model.doctor.Doctor;
import com.med.voll.api.model.patient.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "medvoll_consultas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Consulation implements Serializable {
    private final static long serialVersionUID = -8683875028732445745L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medico")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente")
    private Patient patient;

    private LocalDateTime data;
}
