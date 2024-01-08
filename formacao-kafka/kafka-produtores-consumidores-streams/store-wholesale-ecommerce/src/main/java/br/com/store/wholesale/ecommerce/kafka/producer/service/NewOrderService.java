package br.com.store.wholesale.ecommerce.kafka.producer.service;

import br.com.store.wholesale.ecommerce.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NewOrderService {
    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;
    @Value("${topic.new.order:ECOMMERCE_NEW_ORDER}")
    private String topicNewOrder;

    @Autowired
    private SendEmailService sendEmailService;

    public void newOrder(Order order) {
        log.info("Payload sent to kafka topic: " + topicNewOrder + " with value: " + order);
        kafkaTemplate.send(topicNewOrder, order.getUserId(), order);
        sendEmailService.sendEmail();
    }
}
