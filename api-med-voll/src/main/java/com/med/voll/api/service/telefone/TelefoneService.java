package com.med.voll.api.service.telefone;

import com.med.voll.api.dto.telefone.AtualizaTelefoneDTO;
import com.med.voll.api.dto.telefone.CadastraTelefoneDTO;
import com.med.voll.api.model.medico.Medico;
import com.med.voll.api.model.telefone.Telefone;
import com.med.voll.api.repository.telefone.TelefoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TelefoneService {
    private final TelefoneRepository telefoneRepository;

    public List<Telefone> cadastrar(List<CadastraTelefoneDTO> telefonesDTO, Medico medico) {
        List<Telefone> telefones = new ArrayList<>();
        telefonesDTO.forEach(telefoneDTO -> {
            telefones.add(build(telefoneDTO, medico));
        });
        telefoneRepository.saveAll(telefones);
        return telefones;
    }

    public Telefone atualizar(AtualizaTelefoneDTO atualizaTelefoneDTO){
        Telefone telefone = telefoneRepository.findById(atualizaTelefoneDTO.getId()).get();
        telefone.setDdd(atualizaTelefoneDTO.getDdd() != null ? atualizaTelefoneDTO.getDdd() : telefone.getDdd());
        telefone.setNumero(atualizaTelefoneDTO.getNumero() != null ? atualizaTelefoneDTO.getNumero() : telefone.getNumero());
        return  telefoneRepository.save(telefone);
    }

    private Telefone build(CadastraTelefoneDTO telefoneDTO, Medico medico) {
        return Telefone.builder()
                .ddd(telefoneDTO.getDdd())
                .numero(telefoneDTO.getNumero())
                .medico(medico)
                .build();
    }
}
