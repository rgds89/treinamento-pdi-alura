package com.med.voll.api.dto.address;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateAddressDTO {
    private Long id;
    private String logradouro;
    private String bairro;
    private String numero;
    private String complemento;
    private Long cep;
    private String cidade;
    private String uf;
}
