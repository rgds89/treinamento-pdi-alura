package br.com.alura.roger.series.repository;

import br.com.alura.roger.series.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long>{
    Serie findByTitleContainingIgnoreCase(String title);

    boolean existsByTitle(String name);

    Serie findByTitle(String name);
}
