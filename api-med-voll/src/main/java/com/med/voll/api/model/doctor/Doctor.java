package com.med.voll.api.model.doctor;

import com.med.voll.api.enums.Specialties;
import com.med.voll.api.model.address.Address;
import com.med.voll.api.model.telephone.Telephone;
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
public class Doctor implements Serializable {
    private static final long serialVersionUID = 1373306994731249219L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private Long  crm;
    @Enumerated(EnumType.STRING)
    @Column(name = "especialidade")
    private Specialties specialties;
    @OneToOne
    @JoinColumn(name="id_endereco")
    private Address address;
    @OneToMany
    @JoinColumn(name = "id_medico")
    private List<Telephone> telephones;
    @Builder.Default
    private Boolean ativo = true;
}
