package br.com.alura.roger.series.repository;

import br.com.alura.roger.series.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long>{
}
