package br.com.alura.roger.series.repository;

import br.com.alura.roger.series.model.Season;
import br.com.alura.roger.series.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
    boolean existsBySeasonAndSerie(Integer season, Serie serie);

    Season findBySeason(Integer season);

    @Query("SELECT s FROM Season s WHERE s.serie.title = :nameSerie")
    List<Season> findBySerieTitle(@Param("nameSerie") String nameSerie);

    @Query("SELECT s FROM Season s WHERE s.serie.title = :nameSerie AND s.season = :season")
    Season findBySerieTitleAndSeason(@Param("nameSerie") String name, @Param("season") Integer season);
}
