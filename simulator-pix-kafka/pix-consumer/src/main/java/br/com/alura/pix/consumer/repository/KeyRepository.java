package br.com.alura.pix.consumer.repository;

import br.com.alura.pix.consumer.model.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRepository extends JpaRepository<Key, Integer> {
    Key findByKey(String key);
}