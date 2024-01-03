package br.com.store.wholesale.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class StoreWholesaleEcommerceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoreWholesaleEcommerceApplication.class, args);
    }

}
