package br.com.alura.roger.series.business;

import br.com.alura.roger.series.model.Serie;
import br.com.alura.roger.series.model.enums.Genre;
import br.com.alura.roger.series.model.record.SerieData;
import br.com.alura.roger.series.service.ConsumerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SerieBusiness {
    @Value("${series.url}")
    private String url;
    @Value("${series.apiKey}")
    private String apiKey;

    @Autowired
    private ConsumerApi consumerApi;

    public void printSeries(String nameSeries) {
        var serieData = consumerApi.getData(url + "/?t=" + nameSeries.replace(" ", "+") + "&apikey=" + apiKey, SerieData.class);
        System.out.println(buildSerie(serieData));
    }

    private Serie buildSerie(SerieData serieData) {
        return new Serie(serieData);
//        return Serie.builder()
//                .title(serieData.title())
//                .totalSeasons(serieData.totalSeasons())
//                .imdbRating(Double.valueOf(serieData.imdbRating()))
////                .genre(Genre.valueOf(serieData.genre()))
//                .actor(serieData.actor())
//                .poster(serieData.poster())
//                .plot(serieData.plot())
//                .build();
    }
}
