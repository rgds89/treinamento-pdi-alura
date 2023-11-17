package com.med.voll.api.dto.doctor;

import com.med.voll.api.dto.address.RegisterAddressDTO;
import com.med.voll.api.dto.telephone.RegisterTelephoneDTO;
import com.med.voll.api.enums.Specialties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class RegisterDoctorDTO {
    @NotBlank
    private String nome;
    @NotBlank
    @Email
    private String email;
    @NotNull
    @Digits(integer= 6, fraction = 0)
    private Long  crm;
    @NotNull
    private Specialties specialties;
    @NotNull
    @Valid
    private RegisterAddressDTO endereco;
    @NotNull
    @Valid
    private List<RegisterTelephoneDTO> telefones;
}
