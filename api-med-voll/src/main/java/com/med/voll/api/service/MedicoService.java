package com.med.voll.api.service;

import com.med.voll.api.dto.ListaMedicoDTO;
import com.med.voll.api.dto.MedicoDTO;
import com.med.voll.api.model.Endereco;
import com.med.voll.api.model.Medico;
import com.med.voll.api.model.Telefone;
import com.med.voll.api.repository.EnderecoRepository;
import com.med.voll.api.repository.MedicoRepository;
import com.med.voll.api.repository.TelefoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicoService {
    private final MedicoRepository medicoRepository;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;

    private Medico medico = new Medico();

    private Endereco endereco = new Endereco();

    private List<Telefone> telefones = new ArrayList<>();

    public void cadastrar(MedicoDTO medicoDTO) {
        build(medicoDTO);
        enderecoRepository.save(endereco);
        medicoRepository.save(medico);
        telefoneRepository.saveAll(telefones);

    }

    public List<ListaMedicoDTO> findMedicos(){
        List<ListaMedicoDTO> lista = new ArrayList<>();
        List<Medico>  medicos = medicoRepository.findAll();
        medicos.forEach(x ->{
            ListaMedicoDTO listaMedicoDTO = ListaMedicoDTO.builder()
                    .nome(x.getNome())
                    .email(x.getEmail())
                    .crm(x.getCrm())
                    .especialidade(x.getEspecialidade())
                    .build();
            lista.add(listaMedicoDTO);
        });
        return lista;
    }

    private void build(MedicoDTO medicoDTO) {
        endereco = Endereco.builder()
                .logradouro(medicoDTO.getEndereco().getLogradouro())
                .numero(medicoDTO.getEndereco().getNumero())
                .complemento(medicoDTO.getEndereco().getComplemento())
                .bairro(medicoDTO.getEndereco().getBairro())
                .cidade(medicoDTO.getEndereco().getCidade())
                .uf(medicoDTO.getEndereco().getUf())
                .cep(medicoDTO.getEndereco().getCep())
                .build();

        medico = Medico.builder()
                .nome(medicoDTO.getNome())
                .email(medicoDTO.getEmail())
                .crm(medicoDTO.getCrm())
                .endereco(endereco)
                .especialidade(medicoDTO.getEspecialidade())
                .telefones(telefones)
                .build();

        medicoDTO.getTelefones().forEach(x -> {
            Telefone telefone = Telefone.builder()
                    .ddd(x.getDdd())
                    .numero(x.getNumero())
                    .medico(medico)
                    .build();
            telefones.add(telefone);
        });
    }
}
