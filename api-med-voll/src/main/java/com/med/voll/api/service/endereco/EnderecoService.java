package com.med.voll.api.service.endereco;

import com.med.voll.api.dto.endereco.AtualizaEnderecoDTO;
import com.med.voll.api.dto.endereco.CadastraEnderecoDTO;
import com.med.voll.api.model.endereco.Endereco;
import com.med.voll.api.repository.endereco.EnderecoRepository;
import com.med.voll.api.repository.paciente.PacienteRepositoy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final PacienteRepositoy pacienteRepositoy;

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

    public void atualizar(AtualizaEnderecoDTO atualizaEnderecoDTO ) {
        Endereco endereco = enderecoRepository.findById(atualizaEnderecoDTO.getId()).get();
        enderecoRepository.save(
                Endereco.builder()
                        .logradouro(atualizaEnderecoDTO.getLogradouro() != null || !atualizaEnderecoDTO.getLogradouro().isEmpty() ? atualizaEnderecoDTO.getLogradouro() : endereco.getLogradouro())
                        .numero(atualizaEnderecoDTO.getNumero() != null || !atualizaEnderecoDTO.getNumero().isEmpty() ? atualizaEnderecoDTO.getNumero() : endereco.getNumero())
                        .complemento(atualizaEnderecoDTO.getComplemento() != null || !atualizaEnderecoDTO.getComplemento().isEmpty() ? atualizaEnderecoDTO.getComplemento(): endereco.getComplemento())
                        .bairro(atualizaEnderecoDTO.getBairro() != null || !atualizaEnderecoDTO.getBairro().isEmpty() ? atualizaEnderecoDTO.getBairro() : endereco.getBairro())
                        .cep(atualizaEnderecoDTO.getCep() != null || !atualizaEnderecoDTO.getCep().describeConstable().isEmpty() ? atualizaEnderecoDTO.getCep() : endereco.getCep())
                        .cidade(atualizaEnderecoDTO.getCidade() != null  || !atualizaEnderecoDTO.getCidade().isEmpty() ? atualizaEnderecoDTO.getCidade() : endereco.getCidade())
                        .uf(atualizaEnderecoDTO.getUf() != null || !atualizaEnderecoDTO.getUf().isEmpty() ? atualizaEnderecoDTO.getUf() : endereco.getUf())
                        .build());
    }
}
