package com.med.voll.api.dto.doctor;

import com.med.voll.api.dto.address.UpdateAddressDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateDoctorDTO {
    @NotNull
    private Long id;
    private String nome;
    private String email;
    private UpdateAddressDTO endereco;

}
