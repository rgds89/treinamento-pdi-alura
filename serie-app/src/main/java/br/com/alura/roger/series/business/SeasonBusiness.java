package br.com.alura.roger.series.business;

import br.com.alura.roger.series.model.Episode;
import br.com.alura.roger.series.model.Season;
import br.com.alura.roger.series.model.record.SeasonData;
import br.com.alura.roger.series.model.record.SerieData;
import br.com.alura.roger.series.service.ConsumerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class SeasonBusiness {
    @Value("${series.url}")
    private String url;
    @Value("${series.apiKey}")
    private String apiKey;
    @Autowired
    private ConsumerApi consumerApi;

    public void printSeasons(String nameSeries) {
        List<SeasonData> seasons = new ArrayList<>();
        var serieData = consumerApi.getData(url + "/?t=" + nameSeries.replace(" ", "+") + apiKey, SerieData.class);

        for (int i = 1; i <= serieData.totalSeasons(); i++) {
            var seasonData = consumerApi.getData(url + "/?t=" + nameSeries.replace(" ", "+") + "&season=" + i + apiKey, SeasonData.class);
            seasons.add(seasonData);
        }

        seasons.forEach(System.out::println);
    }

    public void prinBestSeasons(String nameSeries) {
        List<SeasonData> seasonsData = new ArrayList<>();
        var serieData = consumerApi.getData(url + "/?t=" + nameSeries.replace(" ", "+") + apiKey, SerieData.class);

        var totalSeasons = serieData.totalSeasons() != null ? serieData.totalSeasons() : 1;

        for (int i = 1; i <= totalSeasons; i++) {
            var seasonData = consumerApi.getData(url + "/?t=" + nameSeries.replace(" ", "+") + "&season=" + i + apiKey, SeasonData.class);
            seasonsData.add(seasonData);
        }

        List<Episode> episodes = seasonsData.stream()
                .flatMap(s -> s.episodes().stream()
                        .map(e -> new Episode(null, e))
                ).toList();

        var imdbRatingSeason = episodes.stream().collect(Collectors.groupingBy(Episode::getSeason, Collectors.averagingDouble(Episode::getImdbRating)));

        List<Season> seasons = seasonsData.stream()
                .map(s -> new Season(s.season(), s.totalSeasons(), s.title(),
                        imdbRatingSeason.get(s.season())))
                .toList();

        seasons.stream().sorted(Comparator.comparing(Season::getImdbRating).reversed())
                .forEach(System.out::println);
    }
}
