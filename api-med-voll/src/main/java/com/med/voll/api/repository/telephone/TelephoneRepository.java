package com.med.voll.api.repository.telephone;


import com.med.voll.api.model.telephone.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, Long> {
}
