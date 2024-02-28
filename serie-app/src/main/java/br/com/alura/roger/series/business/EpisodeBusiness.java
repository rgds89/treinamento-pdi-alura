package br.com.alura.roger.series.business;

import br.com.alura.roger.series.model.Episode;
import br.com.alura.roger.series.model.record.EpisodeData;
import br.com.alura.roger.series.model.record.SeasonData;
import br.com.alura.roger.series.model.record.SerieData;
import br.com.alura.roger.series.service.ConsumerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class EpisodeBusiness {
    @Value("${series.url}")
    private String url;
    @Value("${series.apiKey}")
    private String apiKey;
//    @Value("${series.season}")
    private String season = "&season=";
//    @Value("${series.episode}")
    private String episode = "&episode=";

    @Autowired
    private ConsumerApi consumerApi;

    public void printEpisode(String nameSeries, int season, int episode) {
        var episodeData = consumerApi.getData(url + "/?t=" + nameSeries.replace(" ", "+") + this.season + season + this.episode + episode + apiKey, EpisodeData.class);
        System.out.println(episodeData);
    }

    public void printAllEpisodesTitle(String nameSeries) {
        List<SeasonData> seasons = new ArrayList<>();
        var serieData = consumerApi.getData(url + "/?t=" + nameSeries.replace(" ", "+") + apiKey, SerieData.class);

        for (int i = 1; i <= serieData.totalSeasons(); i++) {
            var seasonData = consumerApi.getData(url + "/?t=" + nameSeries.replace(" ", "+") + season + i + apiKey, SeasonData.class);
            seasons.add(seasonData);
        }

        seasons.forEach(s -> s.episodes().forEach(e -> System.out.println(e.title())));
    }

    public void printTopFiveEpisodes(String nameSeries) {
        List<SeasonData> seasons = new ArrayList<>();
        var serieData = consumerApi.getData(url + "/?t=" + nameSeries.replace(" ", "+") + apiKey, SerieData.class);

        for (int i = 1; i <= serieData.totalSeasons(); i++) {
            var seasonData = consumerApi.getData(url + "/?t=" + nameSeries.replace(" ", "+") + season + i + apiKey, SeasonData.class);
            seasons.add(seasonData);
        }

        List<Episode> episodes = seasons.stream()
                .flatMap(s -> s.episodes().stream()
                        .map(e -> new Episode(null, e))
                ).toList();

        episodes.stream()
                .sorted(Comparator.comparing(Episode::getImdbRating).reversed())
                .limit(5)
                .forEach(System.out::println);
    }

    public void printEpisodesByYear(String nameSeries, LocalDate date) {
        List<SeasonData> seasons = new ArrayList<>();
        var serieData = consumerApi.getData(url + "/?t=" + nameSeries.replace(" ", "+") + apiKey, SerieData.class);

        for (int i = 1; i <= serieData.totalSeasons(); i++) {
            var seasonData = consumerApi.getData(url + "/?t=" + nameSeries.replace(" ", "+") + season + i + apiKey, SeasonData.class);
            seasons.add(seasonData);
        }

        List<Episode> episodes = seasons.stream()
                .flatMap(s -> s.episodes().stream()
                        .map(e -> new Episode(null, e))
                ).toList();

        episodes.stream()
                .sorted(Comparator.comparing(Episode::getReleased))
                .filter(e -> e.getReleased().isAfter(date))
                .forEach(System.out::println);
    }
}
