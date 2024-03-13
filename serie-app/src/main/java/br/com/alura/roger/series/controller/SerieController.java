package br.com.alura.roger.series.controller;

import br.com.alura.roger.series.model.record.SeasonData;
import br.com.alura.roger.series.model.record.SerieData;
import br.com.alura.roger.series.service.SerieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
