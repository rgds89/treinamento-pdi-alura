package com.med.voll.api.dto.doctor;

import com.med.voll.api.enums.Specialties;
import com.med.voll.api.model.address.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetailDoctorDTO {
    private Long id;
    private String nome;
    private String email;
    private Long crm;
    private Specialties specialties;
    private Address address;
}
