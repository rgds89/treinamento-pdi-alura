package br.com.store.wholesale.ecommerce.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @KafkaListener(topics = "${topic.send.email:ECOMMERCE_SEND_EMAIL}")
    public void consumer(String value) {
        System.out.println("Payload received from kafka topic: ECOMMERCE_SEND_EMAIL with value: " + value);
        System.out.println("Email sent");
    }
}
