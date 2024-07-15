package br.com.alura.ecommerce;

import br.com.alura.ecommerce.consumer.ConsumerService;
import br.com.alura.ecommerce.consumer.ServiceRunner;
import br.com.alura.ecommerce.dispatcher.KafkaDispatcher;
import br.com.alura.ecommerce.message.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.concurrent.ExecutionException;

public class EmailNewOrderService implements ConsumerService<Order> {

    private final KafkaDispatcher<String> emailDispatcher = new KafkaDispatcher<>();
    private static final Integer THREADS = 5;
    private static final String TOPIC_NEW_ORDER = "ECOMMERCE_NEW_ORDER";
    private static final String TOPIC_EMAIL = "ECOMMERCE_SEND_EMAIL";

    public static void main(String[] args) {
        new ServiceRunner<>(EmailNewOrderService::new).start(THREADS);
    }

    public void parse(ConsumerRecord<String, Message<Order>> record) throws ExecutionException, InterruptedException {
        System.out.println("------------------------------------------");
        System.out.println("Processing new order, preparing email");
        System.out.println(record.value());

        var order = record.value().getPayload();
        var emailCode = "Thank you for your order! We are processing your order!";
        emailDispatcher.send(TOPIC_EMAIL,
                order.getEmail(),
                emailCode,
                record.value().getId().continueWith(EmailNewOrderService.class.getSimpleName()));


    }

    public String getTopic() {
        return TOPIC_NEW_ORDER;
    }

    @Override
    public String getConsumerGroup() {
        return EmailNewOrderService.class.getSimpleName();
    }
}
