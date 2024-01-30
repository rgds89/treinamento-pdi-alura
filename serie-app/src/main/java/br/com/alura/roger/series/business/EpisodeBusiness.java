package br.com.alura.roger.series.business;

import br.com.alura.roger.series.business.util.Endpoint;
import br.com.alura.roger.series.model.Episode;
import br.com.alura.roger.series.model.record.EpisodeData;
import br.com.alura.roger.series.model.record.SeasonData;
import br.com.alura.roger.series.model.record.SerieData;
import br.com.alura.roger.series.service.ConsumerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class EpisodeBusiness {
    private static final String URL = Endpoint.URL.getDescription();
    private static final String API_KEY = Endpoint.API_KEY.getDescription();

    @Autowired
    private ConsumerApi consumerApi;

    public void printEpisode(String nameSeries, int season, int episode) {
        var episodeData = consumerApi.getData(URL + nameSeries.replace(" ", "+") + "&season=" + season + "&episode=" + episode + API_KEY, EpisodeData.class);
        System.out.println(episodeData);
    }

    public void printAllEpisodesTitle(String nameSeries) {
        List<SeasonData> seasons = new ArrayList<>();
        var serieData = consumerApi.getData(URL + nameSeries.replace(" ", "+") + API_KEY, SerieData.class);

        for (int i = 1; i <= serieData.totalSeasons(); i++) {
            var seasonData = consumerApi.getData(URL + nameSeries.replace(" ", "+") + "&season=" + i + API_KEY, SeasonData.class);
            seasons.add(seasonData);
        }

        seasons.forEach(s -> s.episodes().forEach(e -> System.out.println(e.title())));
    }

    public void printTopFiveEpisodes(String nameSeries) {
        List<SeasonData> seasons = new ArrayList<>();
        var serieData = consumerApi.getData(URL + nameSeries.replace(" ", "+") + API_KEY, SerieData.class);

        for (int i = 1; i <= serieData.totalSeasons(); i++) {
            var seasonData = consumerApi.getData(URL + nameSeries.replace(" ", "+") + "&season=" + i + API_KEY, SeasonData.class);
            seasons.add(seasonData);
        }

        List<Episode> episodes = seasons.stream()
                .flatMap(s -> s.episodes().stream()
                        .map(e -> new Episode(s.season(), e))
                ).toList();

        episodes.stream()
                .sorted(Comparator.comparing(Episode::getImdbRating).reversed())
                .limit(5)
                .forEach(System.out::println);
    }
}
