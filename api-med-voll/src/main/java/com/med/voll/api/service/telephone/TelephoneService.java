package com.med.voll.api.service.telephone;

import com.med.voll.api.dto.telephone.UpdateTelephoneDTO;
import com.med.voll.api.dto.telephone.RegisterTelephoneDTO;
import com.med.voll.api.model.doctor.Doctor;
import com.med.voll.api.model.telephone.Telephone;
import com.med.voll.api.repository.telephone.TelephoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TelephoneService {
    private final TelephoneRepository telephoneRepository;

    public List<Telephone> cadastrar(List<RegisterTelephoneDTO> telefonesDTO, Doctor doctor) {
        List<Telephone> telephones = new ArrayList<>();
        telefonesDTO.forEach(telefoneDTO -> {
            telephones.add(build(telefoneDTO, doctor));
        });
        telephoneRepository.saveAll(telephones);
        return telephones;
    }

    public Telephone atualizar(UpdateTelephoneDTO updateTelephoneDTO){
        Telephone telephone = telephoneRepository.findById(updateTelephoneDTO.getId()).get();
        telephone.setDdd(updateTelephoneDTO.getDdd() != null ? updateTelephoneDTO.getDdd() : telephone.getDdd());
        telephone.setNumero(updateTelephoneDTO.getNumero() != null ? updateTelephoneDTO.getNumero() : telephone.getNumero());
        return  telephoneRepository.save(telephone);
    }

    private Telephone build(RegisterTelephoneDTO telefoneDTO, Doctor doctor) {
        return Telephone.builder()
                .ddd(telefoneDTO.getDdd())
                .numero(telefoneDTO.getNumero())
                .doctor(doctor)
                .build();
    }
}
