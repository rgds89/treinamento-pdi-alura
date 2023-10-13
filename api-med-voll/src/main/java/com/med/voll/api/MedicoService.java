package com.med.voll.api;

import com.med.voll.api.dto.EnderecoDTO;
import com.med.voll.api.dto.MedicoDTO;
import com.med.voll.api.model.Endereco;
import com.med.voll.api.model.Medico;
import com.med.voll.api.repository.EnderecoRepository;
import com.med.voll.api.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicoService {
    private final MedicoRepository medicoRepository;
    private final EnderecoRepository enderecoRepository;

    public void cadastrar(MedicoDTO medicoDTO){
        medicoRepository.save(buildMedico(medicoDTO));
    }

    private Endereco buildEndereco(EnderecoDTO enderecoDTO){
        Endereco endereco = Endereco.builder()
                .logradouro(enderecoDTO.getLogradouro())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .bairro(enderecoDTO.getBairro())
                .cidade(enderecoDTO.getCidade())
                .uf(enderecoDTO.getUf())
                .cep(enderecoDTO.getCep())
                .build();

        return endereco;
    }

    private Medico buildMedico(MedicoDTO medicoDTO){
        Endereco endereco = buildEndereco(medicoDTO.getEndereco());
        enderecoRepository.save(endereco);
        Medico medico = Medico.builder()
                .nome(medicoDTO.getNome())
                .email(medicoDTO.getEmail())
                .crm(medicoDTO.getCrm())
                .endereco(endereco)
                .especialidade(medicoDTO.getEspecialidade())
                .build();
        return medico;
    }
}
