package com.med.voll.api.dto;

import lombok.Data;

@Data
public class EnderecoDTO {
    private String logradouro;
    private String bairro;
    private String numero;
    private String complemento;
    private Long cep;
    private String cidade;
    private String uf;
}
