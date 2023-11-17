package com.med.voll.api.service.patient;

import com.med.voll.api.dto.patient.UpdatePatientDTO;
import com.med.voll.api.dto.patient.RegisterPatientDTO;
import com.med.voll.api.dto.patient.DetailPatientDTO;
import com.med.voll.api.dto.patient.ListPatientDTO;
import com.med.voll.api.model.patient.Patient;
import com.med.voll.api.repository.patient.PatientRepositoy;
import com.med.voll.api.service.address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepositoy patientRepositoy;
    private final AddressService addressService;

    public DetailPatientDTO cadastrar(RegisterPatientDTO registerPatientDTO) {
        Patient patient = patientRepositoy.save(build(registerPatientDTO));
        return DetailPatientDTO.builder()
                .id(patient.getId())
                .nome(patient.getNome())
                .email(patient.getEmail())
                .address(patient.getAddress())
                .build();
    }

    public DetailPatientDTO atualizar(UpdatePatientDTO updatePatientDTO) {
        Patient patient = patientRepositoy.findById(updatePatientDTO.getId()).get();
        if (updatePatientDTO.getEndereco() != null) {
            addressService.atualizar(updatePatientDTO.getEndereco());        }
        patient.setNome(updatePatientDTO.getNome() != null ? updatePatientDTO.getNome() : patient.getNome());
        patient.setTelefone(updatePatientDTO.getTelefone() != null ? updatePatientDTO.getTelefone() : patient.getTelefone());
        patientRepositoy.save(patient);
        return DetailPatientDTO.builder()
                .id(patient.getId())
                .nome(patient.getNome())
                .email(patient.getEmail())
                .address(patient.getAddress())
                .build();
    }

    public void deletar(Long id) {
        Patient patient = patientRepositoy.findById(id).get();
        patient.setAtivo(false);
        patientRepositoy.save(patient);
    }

    public Page<ListPatientDTO> listar(Pageable paginacao) {
        return patientRepositoy.findAllByAtivoTrue(paginacao).map(ListPatientDTO::new);
    }

    public DetailPatientDTO findPaciente(Long id){
        Patient patient = patientRepositoy.findById(id).get();
        return DetailPatientDTO.builder()
                .id(patient.getId())
                .nome(patient.getNome())
                .email(patient.getEmail())
                .address(patient.getAddress())
                .build();
    }

    private Patient build(RegisterPatientDTO registerPatientDTO) {
        return Patient.builder()
                .nome(registerPatientDTO.getNome())
                .email(registerPatientDTO.getEmail())
                .cpf(registerPatientDTO.getCpf())
                .telefone(registerPatientDTO.getTelefone())
                .address(addressService.cadastrar(registerPatientDTO.getEndereco()))
                .build();
    }
}
