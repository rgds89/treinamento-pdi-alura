package br.com.store.wholesale.ecommerce.kafka.producer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SendEmailService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Value("${topic.send.email:ECOMMERCE_SEND_EMAIL}")
    private String topicSendEmail;

    public void sendEmail() {
        var email = "Thank you for your order! We are processing your order!";
        log.info("Payload sent to kafka topic: " + topicSendEmail + " with value: " + email);
        kafkaTemplate.send(topicSendEmail, email);
    }
}
