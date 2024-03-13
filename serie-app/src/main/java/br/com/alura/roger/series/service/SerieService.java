package br.com.alura.roger.series.service;

import br.com.alura.roger.series.business.EpisodeBusiness;
import br.com.alura.roger.series.business.SeasonBusiness;
import br.com.alura.roger.series.business.SerieBusiness;
import br.com.alura.roger.series.model.record.SeasonData;
import br.com.alura.roger.series.model.record.SerieData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
