package com.med.voll.api.dto.endereco;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AtualizaEnderecoDTO {
    @NotNull
    private Long id;
    private String logradouro;
    private String bairro;
    private String numero;
    private String complemento;
    private Long cep;
    private String cidade;
    private String uf;
}
