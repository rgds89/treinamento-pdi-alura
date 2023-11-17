package com.med.voll.api.service.doctor;


import com.med.voll.api.dto.doctor.DetailDoctorDTO;
import com.med.voll.api.dto.doctor.UpdateDoctorDTO;
import com.med.voll.api.dto.doctor.RegisterDoctorDTO;
import com.med.voll.api.dto.doctor.ListaMedicoDTO;
import com.med.voll.api.model.doctor.Doctor;
import com.med.voll.api.repository.doctor.DoctorRepository;
import com.med.voll.api.service.telephone.TelephoneService;
import com.med.voll.api.service.address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final AddressService addressService;
    private final TelephoneService telephoneService;

    public DetailDoctorDTO cadastrar(RegisterDoctorDTO registerDoctorDTO) {
        Doctor doctor = build(registerDoctorDTO);
        doctorRepository.save(doctor);
        telephoneService.cadastrar(registerDoctorDTO.getTelefones(), doctor);
        return DetailDoctorDTO.builder()
                .id(doctor.getId())
                .nome(doctor.getNome())
                .email(doctor.getEmail())
                .crm(doctor.getCrm())
                .specialties(doctor.getSpecialties())
                .address(doctor.getAddress())
                .build();
    }

    public DetailDoctorDTO atualizar(UpdateDoctorDTO updateDoctorDTO) {
        Doctor doctor = doctorRepository.findById(updateDoctorDTO.getId()).get();
        if(updateDoctorDTO.getEndereco() != null){
            addressService.atualizar(updateDoctorDTO.getEndereco());
        }
        doctor.setNome(updateDoctorDTO.getNome() != null ? updateDoctorDTO.getNome() : doctor.getNome());
        doctor.setEmail(updateDoctorDTO.getEmail() != null ? updateDoctorDTO.getEmail() : doctor.getEmail());
        doctorRepository.save(doctor);
        return DetailDoctorDTO.builder()
                .id(doctor.getId())
                .nome(doctor.getNome())
                .email(doctor.getEmail())
                .crm(doctor.getCrm())
                .specialties(doctor.getSpecialties())
                .address(doctor.getAddress())
                .build();
    }

    public void deletar(Long id) {
        Doctor doctor = doctorRepository.findById(id).get();
        doctor.setAtivo(false);
        doctorRepository.save(doctor);
    }

    public Page<ListaMedicoDTO> findMedicos(Pageable paginacao) {
        return doctorRepository.findAllByAtivoTrue(paginacao).map(ListaMedicoDTO::new);
    }

    public DetailDoctorDTO findMedico(Long id){
        Doctor doctor = doctorRepository.findById(id).get();
        return DetailDoctorDTO.builder()
                .id(doctor.getId())
                .nome(doctor.getNome())
                .email(doctor.getEmail())
                .crm(doctor.getCrm())
                .specialties(doctor.getSpecialties())
                .address(doctor.getAddress())
                .build();
    }

    private Doctor build(RegisterDoctorDTO registerDoctorDTO) {
        return Doctor.builder()
                .nome(registerDoctorDTO.getNome())
                .email(registerDoctorDTO.getEmail())
                .crm(registerDoctorDTO.getCrm())
                .address(addressService.cadastrar(registerDoctorDTO.getEndereco()))
                .specialties(registerDoctorDTO.getSpecialties())
                .build();
    }
}
