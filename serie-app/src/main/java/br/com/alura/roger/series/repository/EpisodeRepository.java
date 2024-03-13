package br.com.alura.roger.series.repository;

import br.com.alura.roger.series.model.Episode;
import br.com.alura.roger.series.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long>{
    List<Episode> findBySeason(Season s);
}
