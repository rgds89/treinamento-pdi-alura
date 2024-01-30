package br.com.alura.roger.series.principal;

import br.com.alura.roger.series.business.EpisodeBusiness;
import br.com.alura.roger.series.business.SeasonBusiness;
import br.com.alura.roger.series.business.SerieBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal {
    private Scanner scanner = new Scanner(System.in);

    @Autowired
    private SerieBusiness serie;
    @Autowired
    private SeasonBusiness season;
    @Autowired
    private EpisodeBusiness episode;


    public void displayMenu() {
        System.out.println("Informe a série que deseja buscar: ");
        var nameSeries = scanner.nextLine();
        serie.printSeries(nameSeries);

        System.out.println("Deseja ver as temporadas? (S/N)");
        if (scanner.nextLine().equalsIgnoreCase("S")) {
            season.printSeasons(nameSeries);
        }

        System.out.println("Deseja ver os episódios? (S/N)");
        if (scanner.nextLine().equalsIgnoreCase("S")) {
            System.out.println("Informe a temporada: ");
            var season = scanner.nextInt();
            System.out.println("Informe o episódio: ");
            var episodeNumber = scanner.nextInt();
            episode.printEpisode(nameSeries, season, episodeNumber);
        }

        System.out.println("Deseja imprimir o titulo de cada episódio? (S/N)");
        if (scanner.nextLine().equalsIgnoreCase("S")) {
            episode.printAllEpisodesTitle(nameSeries);
        }

        System.out.println("Deseja imprimir os 5 episódios mais bem avaliados? (S/N)");
        if (scanner.nextLine().equalsIgnoreCase("S")) {
            episode.printTopFiveEpisodes(nameSeries);
        }
    }


}
