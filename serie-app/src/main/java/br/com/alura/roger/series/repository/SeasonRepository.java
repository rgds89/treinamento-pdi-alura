package br.com.alura.roger.series.repository;

import br.com.alura.roger.series.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
    boolean existsBySeason(Integer season);

    Season findBySeason(Integer season);

    @Query("SELECT s FROM Season s WHERE s.serie.title = :nameSerie")
    List<Season> findBySerieTitle(@Param("nameSerie") String nameSerie);
}
