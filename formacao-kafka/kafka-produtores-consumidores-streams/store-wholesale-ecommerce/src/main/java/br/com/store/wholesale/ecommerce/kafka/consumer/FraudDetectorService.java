package br.com.store.wholesale.ecommerce.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FraudDetectorService {
    @KafkaListener(topics = "${topic.new.order:ECOMMERCE_NEW_ORDER}")
    public void consumer(String value) {
        System.out.println("Payload received from kafka topic: ECOMMERCE_NEW_ORDER with value: " + value);
        System.out.println("Order processed");
    }
}
