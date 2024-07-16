package br.com.alura.ecommerce.consumer;

import java.util.Map;
import java.util.concurrent.Callable;

public class ServiceProvider<T> implements Callable<Void> {
    private final ServiceFactory<T> factory;

    public ServiceProvider(ServiceFactory<T> factory) {
        this.factory = factory;
    }

    public Void call() throws Exception {
        var service = factory.create();
        try (var serviceKafka = new KafkaService<>(service.getConsumerGroup(), service.getTopic(), service::parse, Map.of())) {
            serviceKafka.run();
        }

        return null;
    }
}
