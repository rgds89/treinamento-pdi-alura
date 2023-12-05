package com.med.voll.api.repository.consulation;

import com.med.voll.api.model.consulation.Consulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsulationRepository extends JpaRepository<Consulation, Long> {
}
