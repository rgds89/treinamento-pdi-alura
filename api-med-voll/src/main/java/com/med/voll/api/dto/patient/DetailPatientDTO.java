package com.med.voll.api.dto.patient;

import com.med.voll.api.model.address.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetailPatientDTO {
    private Long id;
    private String nome;
    private String email;
    private Address address;
}
