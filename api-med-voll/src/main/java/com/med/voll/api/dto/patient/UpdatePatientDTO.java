package com.med.voll.api.dto.patient;

import com.med.voll.api.dto.address.UpdateAddressDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatePatientDTO {
    @NotNull
    private Long id;
    private String nome;
    private String telefone;
    private UpdateAddressDTO endereco;
}
