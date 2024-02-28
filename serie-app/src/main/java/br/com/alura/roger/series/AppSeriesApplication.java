package br.com.alura.roger.series;

import br.com.alura.roger.series.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppSeriesApplication {
    @Autowired
    private Principal principal;

    public static void main(String[] args) {
        SpringApplication.run(AppSeriesApplication.class, args);
    }

}
