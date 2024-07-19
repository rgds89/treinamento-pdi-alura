package br.com.alura.pix.producer.respository;

import br.com.alura.pix.producer.model.Pix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PixRepository extends JpaRepository<Pix, Long> {
}
