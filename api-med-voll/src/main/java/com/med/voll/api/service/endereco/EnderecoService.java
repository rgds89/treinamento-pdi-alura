package com.med.voll.api.service.endereco;

import com.med.voll.api.dto.endereco.CadastraEnderecoDTO;
import com.med.voll.api.model.endereco.Endereco;
import com.med.voll.api.repository.endereco.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    public Endereco cadastrar(CadastraEnderecoDTO enderecoDTO){
        Endereco endereco = build(enderecoDTO);
        enderecoRepository.save(endereco);
        return endereco;
    }

    private Endereco build (CadastraEnderecoDTO enderecoDTO){
        return Endereco.builder()
                .logradouro(enderecoDTO.getLogradouro())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .bairro(enderecoDTO.getBairro())
                .cidade(enderecoDTO.getCidade())
                .uf(enderecoDTO.getUf())
                .cep(enderecoDTO.getCep())
                .build();
    }
}
