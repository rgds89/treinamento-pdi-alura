package br.com.store.wholesale.ecommerce.kafka.producer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
//@RequiredArgsConstructor
public class NewOrderService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Value("${topic.new.order:ECOMMERCE_NEW_ORDER}")
    private String topicNewOrder;
    @Value("${topic.send.email:ECOMMERCE_SEND_EMAIL}")
    private String topicSendEmail;

    public void newOrder(String value) {
        log.info("Payload sent to kafka topic: " + topicNewOrder + " with value: " + value);
        kafkaTemplate.send(topicNewOrder, value);

        var email = "Thank you for your order! We are processing your order!";
        log.info("Payload sent to kafka topic: " + topicSendEmail + " with value: " + email);
        kafkaTemplate.send(topicSendEmail, email);
    }
}
