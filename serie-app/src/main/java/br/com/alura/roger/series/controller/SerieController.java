package br.com.alura.roger.series.controller;

import br.com.alura.roger.series.model.record.EpisodeData;
import br.com.alura.roger.series.model.record.SeasonData;
import br.com.alura.roger.series.model.record.SerieData;
import br.com.alura.roger.series.service.SerieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/series")
@RequiredArgsConstructor
public class SerieController {
    private final SerieService serieService;

    @GetMapping("/{name}")
    public ResponseEntity<SerieData> getSerie(@PathVariable String name) {
        return ResponseEntity.ok().body(serieService.getSerie(name));
    }

    @GetMapping("/{name}/seasons")
    public ResponseEntity<List<SeasonData>> getSeasons(@PathVariable String name) {
        return ResponseEntity.ok().body(serieService.getSeasons(name));
    }

    @GetMapping("/{name}/bestSeasons")
    public ResponseEntity<List<SeasonData>> listBestSeasons(@PathVariable String name) {
        return ResponseEntity.ok().body(serieService.listBestSeasons(name));
    }

    @GetMapping("/{name}/season/{season}")
    public ResponseEntity<SeasonData> getSeason(@PathVariable String name, @PathVariable int season) {
        return ResponseEntity.ok().body(serieService.getSeason(name, season));
    }

    @GetMapping("/{name}/episodes")
    public ResponseEntity<List<EpisodeData>> getEpisodes(@PathVariable String name) {
        return ResponseEntity.ok().body(serieService.getEpisodes(name));
    }

    @GetMapping("/{name}/topFiveEpisodes")
    public ResponseEntity<List<EpisodeData>> getTopFiveEpisodes(@PathVariable String name) {
        return ResponseEntity.ok().body(serieService.getTopFiveEpisodes(name));
    }

    @GetMapping("/{name}/season/{season}/episode/{episode}")
    public ResponseEntity<EpisodeData> getEpisode(@PathVariable String name, @PathVariable int season, @PathVariable int episode) {
        return ResponseEntity.ok().body(serieService.getEpisode(name, season, episode));
    }

    @GetMapping("/{name}/episodes/{year}")
    public ResponseEntity<List<EpisodeData>> getEpisodesByYear(@PathVariable String name, @PathVariable int year) {
        LocalDate date = LocalDate.of(year, 1, 1);
        return ResponseEntity.ok().body(serieService.getEpisodesByYear(name, date));
    }
}
