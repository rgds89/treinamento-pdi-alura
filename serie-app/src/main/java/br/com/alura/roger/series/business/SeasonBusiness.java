package br.com.alura.roger.series.business;

import br.com.alura.roger.series.business.util.Endpoint;
import br.com.alura.roger.series.model.record.SeasonData;
import br.com.alura.roger.series.model.record.SerieData;
import br.com.alura.roger.series.service.ConsumerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class SeasonBusiness {
    private static final String URL = Endpoint.URL.getDescription();
    private static final String API_KEY = Endpoint.API_KEY.getDescription();

    @Autowired
    private ConsumerApi consumerApi;
    public void printSeasons(String nameSeries) {
        List<SeasonData> seasons = new ArrayList<>();
        var serieData = consumerApi.getData(URL + nameSeries.replace(" ", "+") + API_KEY, SerieData.class);

        for (int i = 1; i <= serieData.totalSeasons(); i++) {
            var seasonData = consumerApi.getData(URL + nameSeries.replace(" ", "+") + "&season=" + i + API_KEY, SeasonData.class);
            seasons.add(seasonData);
        }

        seasons.forEach(System.out::println);
    }
}
