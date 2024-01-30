package br.com.alura.roger.series.business;

import br.com.alura.roger.series.business.util.Endpoint;
import br.com.alura.roger.series.model.record.SerieData;
import br.com.alura.roger.series.service.ConsumerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SerieBusiness {
    private static final String URL = Endpoint.URL.getDescription();
    private static final String API_KEY = Endpoint.API_KEY.getDescription();

    @Autowired
    private ConsumerApi consumerApi;

    public void printSeries(String nameSeries) {
        var serieData = consumerApi.getData(URL + nameSeries.replace(" ", "+") + API_KEY, SerieData.class);
        System.out.println(serieData);
    }
}
