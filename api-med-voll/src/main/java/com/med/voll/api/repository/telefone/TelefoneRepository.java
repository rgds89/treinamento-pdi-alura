package com.med.voll.api.repository.telefone;


import com.med.voll.api.model.telefone.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
