package com.med.voll.api.repository.doctor;


import com.med.voll.api.enums.Specialties;
import com.med.voll.api.model.doctor.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            SELECT d FROM Doctor d
            WHERE d.ativo= true AND d.id = :id
            """)
    boolean isDoctorActive(Long id);

    @Query("""
            SELECT d FROM Doctor d
            WHERE d.ativo= true AND d.id = :id
            AND d.id NOT IN (
                SELECT c.doctor.id FROM Consulation c
                WHERE c.data = :data
            )
            """)
    boolean isDoctorAvailable(Long id, LocalDateTime data);

    @Query("""
            SELECT d FROM Doctor d
            WHERE d.ativo= true AND d.specialties = :specialties
            AND d.id NOT IN (
                SELECT c.doctor.id FROM Consulation c
                WHERE c.data = :data
            )
            order by rand()
            limit 1
            """)
    Optional<Doctor> findByEspecialidade(Specialties specialties, LocalDateTime data);
}
