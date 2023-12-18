package com.med.voll.api.service.consulation;

import com.med.voll.api.business.validation.ValidateScheduleConsulation;
import com.med.voll.api.dto.consulation.RegisterCosulationDTO;
import com.med.voll.api.enums.Specialties;
import com.med.voll.api.model.consulation.Consulation;
import com.med.voll.api.model.doctor.Doctor;
import com.med.voll.api.model.patient.Patient;
import com.med.voll.api.repository.consulation.ConsulationRepository;
import com.med.voll.api.repository.doctor.DoctorRepository;
import com.med.voll.api.repository.patient.PatientRepositoy;
import com.med.voll.api.service.consulation.exception.ValidationConsulationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsulationService {
    private ConsulationRepository consulationRepository;
    private DoctorRepository doctorRepository;
    private PatientRepositoy patientRepository;

    private final List<ValidateScheduleConsulation> validateScheduleConsulationList;

    public void createConsulation(RegisterCosulationDTO registerCosulationDTO) {
        validateScheduleConsulationList.forEach(validateScheduleConsulation -> validateScheduleConsulation.validate(registerCosulationDTO));
        Doctor doctor = getDoctor(registerCosulationDTO.getIdMedico(), registerCosulationDTO.getEspecialidade(), registerCosulationDTO.getData());
        Patient patient = getPatient(registerCosulationDTO.getIdPaciente());

        consulationRepository.save(buildConsulation(registerCosulationDTO, doctor, patient));
    }

    private Doctor getDoctor(Long id, Specialties especialidade, LocalDateTime data) {
        if (id != null) {
            return doctorRepository.findById(id).orElseThrow(() -> new ValidationConsulationException("Médico não encontrado"));
        }else if (especialidade !=  null){
            return doctorRepository.findByEspecialidade(especialidade, data).orElseThrow(() -> new ValidationConsulationException("Médico não encontrado"));
        }else{
            throw new ValidationConsulationException("Especialidade ou Médico não informado");
        }
    }

    private Patient getPatient(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new ValidationConsulationException("Paciente não encontrado"));
    }

    private Consulation buildConsulation(RegisterCosulationDTO registerCosulationDTO, Doctor doctor, Patient patient) {
        return Consulation.builder()
                .doctor(doctor)
                .patient(patient)
                .data(registerCosulationDTO.getData())
                .build();
    }
}
