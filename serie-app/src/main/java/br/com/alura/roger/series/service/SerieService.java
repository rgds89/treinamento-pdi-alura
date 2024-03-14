package br.com.alura.roger.series.service;

import br.com.alura.roger.series.business.EpisodeBusiness;
import br.com.alura.roger.series.business.SeasonBusiness;
import br.com.alura.roger.series.business.SerieBusiness;
import br.com.alura.roger.series.model.record.EpisodeData;
import br.com.alura.roger.series.model.record.SeasonData;
import br.com.alura.roger.series.model.record.SerieData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SerieService {
    private final SerieBusiness serieBusiness;
    private final SeasonBusiness seasonBusiness;
    private final EpisodeBusiness episodeBusiness;
    public SerieData getSerie(String name) {
        return serieBusiness.getSerie(name);
    }

    public List<SeasonData> getSeasons(String name) {
        SerieData serie = serieBusiness.getSerie(name);
        List<SeasonData> seasons = seasonBusiness.getSeasons(serie);
        return seasons;
    }

    public List<SeasonData> listBestSeasons(String nameSeries) {
        return seasonBusiness.listBestSeasons(nameSeries);
    }

    public SeasonData getSeason(String nameSeries, int season) {
        return seasonBusiness.getSeason(nameSeries, season);
    }

    public List<EpisodeData> getEpisodes(String name) {
        return episodeBusiness.getEpisodes(name);
    }

    public List<EpisodeData> getTopFiveEpisodes(String name) {
        return episodeBusiness.getTopFiveEpisodes(name);
    }

    public EpisodeData getEpisode(String name, int season, int episode) {
        return episodeBusiness.getEpisode(name, season, episode);
    }

    public List<EpisodeData> getEpisodesByYear(String name, LocalDate date) {
        return episodeBusiness.getEpisodesByYear(name, date);
    }
}
