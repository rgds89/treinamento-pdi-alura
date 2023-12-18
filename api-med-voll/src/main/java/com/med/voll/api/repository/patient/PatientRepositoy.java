package com.med.voll.api.repository.patient;

import com.med.voll.api.model.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PatientRepositoy extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            SELECT p FROM Patient p
            WHERE p.ativo= true AND p.id = :id
            """)
    boolean isPatientActive(Long id);
}
