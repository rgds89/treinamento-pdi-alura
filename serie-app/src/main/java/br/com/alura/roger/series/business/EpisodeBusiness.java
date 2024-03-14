package br.com.alura.roger.series.business;

import br.com.alura.roger.series.model.Episode;
import br.com.alura.roger.series.model.Season;
import br.com.alura.roger.series.model.Serie;
import br.com.alura.roger.series.model.record.EpisodeData;
import br.com.alura.roger.series.model.record.SeasonData;
import br.com.alura.roger.series.model.record.SerieData;
import br.com.alura.roger.series.repository.EpisodeRepository;
import br.com.alura.roger.series.repository.SeasonRepository;
import br.com.alura.roger.series.repository.SerieRepository;
import br.com.alura.roger.series.service.ConsumerApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
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


    private final SerieRepository serieRepository;
    private final EpisodeRepository episodeRepository;
    private final SeasonRepository seasonRepository;


    public List<EpisodeData> getEpisodes(String name) {
        List<EpisodeData> episodesApi = new ArrayList<>();
        List<EpisodeData> episodes = new ArrayList<>();
        Serie serie = serieRepository.findByTitle(name);
        serie.getSeasons().forEach(s -> {
            if (s.getEpisodes().isEmpty()) {
                episodesApi.addAll(getEpisodesApi(name, s.getSeason()));
                episodesApi.forEach(e -> {
                    Episode episode = buildEpisode(e, s);
                    episodeRepository.save(episode);
                    episodes.add(e);
                });
            } else {
                s.getEpisodes().forEach(e -> episodes.add(buildEpisodeData(e)));
            }
        });
        return episodes;
    }

    public List<EpisodeData> getTopFiveEpisodes(String nameSeries) {
        List<EpisodeData> episodesData = new ArrayList<>();

        List<Episode> episodes = episodeRepository.findAll();

        episodes.stream()
                .sorted(Comparator.comparing(Episode::getImdbRating).reversed())
                .limit(5)
                .forEach(e -> episodesData.add(buildEpisodeData(e)));
        return episodesData;
    }

    public EpisodeData getEpisode(String name, int season, int episode) {
        Season seasonEntity = seasonRepository.findBySerieTitleAndSeason(name, season);
        Episode ep = episodeRepository.findBySeasonAndEpisode(seasonEntity.getSeason(), episode);
        return buildEpisodeData(ep);
    }

    public List<EpisodeData> getEpisodesByYear(String name, LocalDate date) {
        List<Season> seasons = seasonRepository.findBySerieTitle(name);
        List<EpisodeData> episodes = new ArrayList<>();
        seasons.forEach(s -> {
            s.getEpisodes().stream()
                    .filter(e -> e.getReleased().isAfter(date))
                    .forEach(e -> episodes.add(buildEpisodeData(e)));
        });
        return episodes;
    }

    private EpisodeData buildEpisodeData(Episode episode) {
        return EpisodeData.builder()
                .title(episode.getTitle())
                .episode(episode.getEpisode())
                .imdbRating(episode.getImdbRating().toString())
                .released(episode.getReleased().toString())
                .build();
    }

    private Episode buildEpisode(EpisodeData e, Season s) {
        return new Episode(s, e);
    }

    private List<EpisodeData> getEpisodesApi(String nameSeries, Integer season) {
        SeasonData seasonData = consumerApi.getData(url + "/?t=" + nameSeries.replace(" ", "+") + "&season=" + season + apiKey, SeasonData.class);
        return seasonData.episodes();
    }
}
