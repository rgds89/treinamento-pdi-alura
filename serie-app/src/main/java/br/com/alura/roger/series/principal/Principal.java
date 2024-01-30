package br.com.alura.roger.series.principal;

import br.com.alura.roger.series.model.record.EpisodeData;
import br.com.alura.roger.series.model.record.SeasonData;
import br.com.alura.roger.series.model.record.SerieData;
import br.com.alura.roger.series.service.ConsumerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private static final String URL = "http://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=21568e41";

    @Autowired
    private ConsumerApi consumerApi;

    public void displayMenu() {
        System.out.println("Informe a série que deseja buscar: ");
        var nameSeries = scanner.nextLine();
        printSeries(nameSeries);

        System.out.println("Deseja ver as temporadas? (S/N)");
        if (scanner.nextLine().equalsIgnoreCase("S")) {
            printSeasons(nameSeries);
        }

        System.out.println("Deseja ver os episódios? (S/N)");
        if (scanner.nextLine().equalsIgnoreCase("S")) {
            System.out.println("Informe a temporada: ");
            var season = scanner.nextInt();
            System.out.println("Informe o episódio: ");
            var episode = scanner.nextInt();
            printEpisode(nameSeries, season, episode);
        }

        System.out.println("Deseja imprimir o titulo de cada episódio? (S/N)");
        if(scanner.nextLine().equalsIgnoreCase("S")) {
            printAllEpisodesTitle(nameSeries);
        }
    }

    private void printAllEpisodesTitle(String nameSeries) {
        List<SeasonData> seasons = new ArrayList<>();
        var serieData = consumerApi.getData(URL + nameSeries.replace(" ", "+") + API_KEY, SerieData.class);

        for (int i = 1; i <= serieData.totalSeasons(); i++) {
            var seasonData = consumerApi.getData(URL + nameSeries.replace(" ", "+") + "&season=" + i + API_KEY, SeasonData.class);
            seasons.add(seasonData);
        }

        seasons.forEach(s -> s.episodes().forEach(e -> System.out.println(e.title())));
    }

    private void printSeries(String nameSeries) {
        var serieData = consumerApi.getData(URL + nameSeries.replace(" ", "+") + API_KEY, SerieData.class);
        System.out.println(serieData);
    }

    private void printSeasons(String nameSeries) {
        List<SeasonData> seasons = new ArrayList<>();
        var serieData = consumerApi.getData(URL + nameSeries.replace(" ", "+") + API_KEY, SerieData.class);

        for (int i = 1; i <= serieData.totalSeasons(); i++) {
            var seasonData = consumerApi.getData(URL + nameSeries.replace(" ", "+") + "&season=" + i + API_KEY, SeasonData.class);
            seasons.add(seasonData);
        }

        seasons.forEach(System.out::println);
    }

    private void printEpisode(String nameSeries, int season, int episode) {
        var episodeData = consumerApi.getData(URL + nameSeries.replace(" ", "+") + "&season=" + season + "&episode=" + episode + API_KEY, EpisodeData.class);
        System.out.println(episodeData);
    }

}
