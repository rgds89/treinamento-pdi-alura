package br.com.alura.roger.series.business;

import br.com.alura.roger.series.model.Episode;
import br.com.alura.roger.series.model.Season;
import br.com.alura.roger.series.model.Serie;
import br.com.alura.roger.series.model.record.SeasonData;
import br.com.alura.roger.series.model.record.SerieData;
import br.com.alura.roger.series.repository.EpisodeRepository;
import br.com.alura.roger.series.repository.SeasonRepository;
import br.com.alura.roger.series.repository.SerieRepository;
import br.com.alura.roger.series.service.ConsumerApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class SeasonBusiness {
    @Value("${series.url}")
    private String url;
    @Value("${series.apiKey}")
    private String apiKey;

    private final ConsumerApi consumerApi;
    private final SeasonRepository seasonRepository;
    private final SerieRepository serieRepository;
    private final EpisodeRepository episodeRepository;

    public List<SeasonData> getSeasons(SerieData serieData) {
        List<SeasonData> seasonsApi = getSeasonsApi(serieData.title(), serieData.totalSeasons());
        List<SeasonData> seasons = new ArrayList<>();
        seasonsApi.forEach(s -> {
            if (!seasonRepository.existsBySeason(s.season())) {
                Serie serie = serieRepository.findByTitle(serieData.title());
                Season season = buildSeason(s, serie);
                seasonRepository.save(season);
                seasons.add(buildSeasonData(season));
            } else {
                Season season = seasonRepository.findBySeason(s.season());
                seasons.add(buildSeasonData(season));
            }
        });
        return seasons;
    }

    private SeasonData buildSeasonData(Season season) {
        return SeasonData.builder()
                .season(season.getSeason())
                .totalSeasons(season.getTotalSeasons())
                .title(season.getTitle())
                .build();
    }

    private Season buildSeason(SeasonData seasonData, Serie serie) {
        return new Season(seasonData.season(), seasonData.totalSeasons(), seasonData.title(), null, serie);
    }

    private List<SeasonData> getSeasonsApi(String nameSeries, Integer totalSeasons) {
        List<SeasonData> seasons = new ArrayList<>();
        for (int i = 1; i <= totalSeasons; i++) {
            var seasonData = consumerApi.getData(url + "/?t=" + nameSeries.replace(" ", "+") + "&season=" + i + apiKey, SeasonData.class);
            seasons.add(seasonData);
        }
        return seasons;
    }

    public List<SeasonData> listBestSeasons(String nameSeries) {
        List<SeasonData> seasonsData = new ArrayList<>();
        try {
            List<Season> seasons = seasonRepository.findBySerieTitle(nameSeries);
            seasons.forEach(s -> {
                if (s.getImdbRating() == null) {
                    List<Episode> episodes = episodeRepository.findBySeason(s);
                    var imdbRating = episodes.stream().collect(Collectors.groupingBy(Episode::getSeason, Collectors.averagingDouble(Episode::getImdbRating)));
                    s.setImdbRating(imdbRating.get(s.getSeason()));
                    seasonRepository.save(s);
                }
            });

            seasons.stream().sorted(Comparator.comparing(Season::getImdbRating).reversed())
                    .forEach(s -> {
                        SeasonData seasonData = buildSeasonData(s);
                        seasonsData.add(seasonData);
                    });

        } catch (Exception e) {
            throw new RuntimeException("Serie not found");
        }
        return seasonsData;
    }


}
