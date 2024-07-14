package br.com.alura.ecommerce;

import br.com.alura.ecommerce.consumer.KafkaService;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ServiceProvider {
    public <T> void run(ServiceFactory<T> factory) throws ExecutionException, InterruptedException {
        var service = factory.create();
        try (var serviceKafka = new KafkaService(service.getConsumerGroup(), service.getTopic(), service::parse, Map.of())) {
            serviceKafka.run();
        }
    }
}
