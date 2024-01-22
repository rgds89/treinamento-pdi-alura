package br.com.alura.roger.series;

import br.com.alura.roger.series.service.ConsumerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppSeriesApplication implements CommandLineRunner {
	@Autowired
	private ConsumerApi consumerApi;

	public static void main(String[] args) {
		SpringApplication.run(AppSeriesApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
		var serieData = consumerApi.getData("http://www.omdbapi.com/?t=The+Walking+Dead&apikey=21568e41");
		System.out.println(serieData);

	}

}
