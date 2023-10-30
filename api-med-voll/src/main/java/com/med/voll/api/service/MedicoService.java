package com.med.voll.api.service;

import com.med.voll.api.dto.AtualizaMedicoDTO;
import com.med.voll.api.dto.AtualizaTelefoneDTO;
import com.med.voll.api.dto.ListaMedicoDTO;
import com.med.voll.api.dto.CadastraMedicoDTO;
import com.med.voll.api.model.Endereco;
import com.med.voll.api.model.Medico;
import com.med.voll.api.model.Telefone;
import com.med.voll.api.repository.EnderecoRepository;
import com.med.voll.api.repository.MedicoRepository;
import com.med.voll.api.repository.TelefoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MedicoService {
    private final MedicoRepository medicoRepository;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;

    private Medico medico = new Medico();

    private Endereco endereco = new Endereco();

    private List<Telefone> telefones = new ArrayList<>();

    public void cadastrar(CadastraMedicoDTO cadastraMedicoDTO) {
        build(cadastraMedicoDTO);
        enderecoRepository.save(endereco);
        medicoRepository.save(medico);
        telefoneRepository.saveAll(telefones);

    }

    public Page<ListaMedicoDTO> findMedicos(Pageable paginacao){
        return medicoRepository.findAllByAtivoTrue(paginacao).map(ListaMedicoDTO::new);
    }

    private void build(CadastraMedicoDTO cadastraMedicoDTO) {
        endereco = Endereco.builder()
                .logradouro(cadastraMedicoDTO.getEndereco().getLogradouro())
                .numero(cadastraMedicoDTO.getEndereco().getNumero())
                .complemento(cadastraMedicoDTO.getEndereco().getComplemento())
                .bairro(cadastraMedicoDTO.getEndereco().getBairro())
                .cidade(cadastraMedicoDTO.getEndereco().getCidade())
                .uf(cadastraMedicoDTO.getEndereco().getUf())
                .cep(cadastraMedicoDTO.getEndereco().getCep())
                .build();

        medico = Medico.builder()
                .nome(cadastraMedicoDTO.getNome())
                .email(cadastraMedicoDTO.getEmail())
                .crm(cadastraMedicoDTO.getCrm())
                .endereco(endereco)
                .especialidade(cadastraMedicoDTO.getEspecialidade())
                .telefones(telefones)
                .build();

        cadastraMedicoDTO.getTelefones().forEach(x -> {
            Telefone telefone = Telefone.builder()
                    .ddd(x.getDdd())
                    .numero(x.getNumero())
                    .medico(medico)
                    .build();
            telefones.add(telefone);
        });
    }

    public AtualizaMedicoDTO atualizar(AtualizaMedicoDTO atualizaMedicoDTO) {
        medico = medicoRepository.findById(atualizaMedicoDTO.getId()).get();
        Telefone telefone = telefoneRepository.findById(atualizaMedicoDTO.getTelefone().getId()).get();

        telefone.setDdd(atualizaMedicoDTO.getTelefone().getDdd() != null ?
                !atualizaMedicoDTO.getTelefone().getDdd().isEmpty() ?
                        atualizaMedicoDTO.getTelefone().getDdd() : telefone.getDdd() :
                telefone.getDdd());
        telefone.setNumero(atualizaMedicoDTO.getTelefone().getNumero() != null ?
                !atualizaMedicoDTO.getTelefone().getNumero().isEmpty() ?
                        atualizaMedicoDTO.getTelefone().getNumero() : telefone.getNumero() :
                telefone.getNumero());

        medico.setNome(atualizaMedicoDTO.getNome() != null ?
                !atualizaMedicoDTO.getNome().isEmpty() ?
                        atualizaMedicoDTO.getNome() : medico.getNome() :
                medico.getNome());

        medico.setEmail(atualizaMedicoDTO.getEmail() != null ?
                !atualizaMedicoDTO.getEmail().isEmpty() ?
                        atualizaMedicoDTO.getEmail() : medico.getEmail() :
                medico.getEmail());

        telefoneRepository.save(telefone);
        medicoRepository.save(medico);

        return  AtualizaMedicoDTO.builder()
                .id(medico.getId())
                .nome(medico.getNome())
                .email(medico.getEmail())
                .telefone(
                        AtualizaTelefoneDTO.builder()
                                .ddd(telefone.getDdd())
                                .numero(telefone.getNumero())
                                .build())
                .build();
    }

    public void deletar(Long id) {
        medico = medicoRepository.findById(id).get();
        medico.setAtivo(false);
        medicoRepository.save(medico);
    }
}
