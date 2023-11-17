package com.med.voll.api.repository.patient;

import com.med.voll.api.model.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepositoy extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByAtivoTrue(Pageable paginacao);
}
