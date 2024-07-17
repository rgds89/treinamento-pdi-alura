package br.com.alura.pix.consumer.repository;

import br.com.alura.pix.consumer.model.Pix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PixRepository  extends JpaRepository<Pix, Integer> {
   Pix findByIdentifier(String identifier);
}
