package br.com.alura.roger.series.repository;

import br.com.alura.roger.series.model.Episode;
import br.com.alura.roger.series.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long>{
    List<Episode> findBySeason(Season s);

    @Query("SELECT e FROM Episode e WHERE e.season.season = :season AND e.episode = :episode")
    Episode findBySeasonAndEpisode(@Param("season") int season, @Param("episode") int episode);
}
