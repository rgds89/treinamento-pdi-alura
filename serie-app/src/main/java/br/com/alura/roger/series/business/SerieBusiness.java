package br.com.alura.roger.series.business;

import br.com.alura.roger.series.model.Serie;
import br.com.alura.roger.series.model.record.SerieData;
import br.com.alura.roger.series.repository.SerieRepository;
import br.com.alura.roger.series.service.ConsumerApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SerieBusiness {
    @Value("${series.url}")
    private String url;
    @Value("${series.apiKey}")
    private String apiKey;

    private final ConsumerApi consumerApi;
    private final SerieRepository serieRepository;


    public SerieData getSerie(String name) {
        if(serieRepository.existsByTitle(name)){
            Serie serie = serieRepository.findByTitle(name);
            SerieData serieData = buildSerieData(serie);
            return serieData;
        }else {
            SerieData serieData = getSerieApi(name);
            Serie serie = buildSerie(serieData);
            serieRepository.save(serie);
            return serieData;
        }
    }

    private SerieData getSerieApi(String nameSeries) {
        var serieData = consumerApi.getData(url + "/?t=" + nameSeries.replace(" ", "+") + "&apikey=" + apiKey, SerieData.class);
        return serieData;
    }

    private Serie buildSerie(SerieData serieData) {
        return new Serie(serieData);
    }

    private SerieData buildSerieData(Serie serie) {
        return SerieData.builder()
                .title(serie.getTitle())
                .totalSeasons(serie.getTotalSeasons())
                .imdbRating(serie.getImdbRating().toString())
                .genre(serie.getGenre().toString())
                .actor(serie.getActor())
                .poster(serie.getPoster())
                .plot(serie.getPlot())
                .build();
    }
}
