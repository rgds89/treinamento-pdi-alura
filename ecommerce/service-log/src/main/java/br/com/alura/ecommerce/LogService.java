package br.com.alura.ecommerce;

import br.com.alura.ecommerce.consumer.ConsumerService;
import br.com.alura.ecommerce.consumer.ServiceRunner;
import br.com.alura.ecommerce.message.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class LogService implements ConsumerService<String> {

    private static final String TOPIC_ECOMMERCE = "ECOMMERCE";

    public static void main(String[] args) {
        new ServiceRunner<>(LogService::new).start(1);
    }

    public void parse(ConsumerRecord<String, Message<String>> record) {
        System.out.println("------------------------------------------");
        System.out.println("LOG: " + record.topic());
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
    }

    @Override
    public String getTopic() {
        return TOPIC_ECOMMERCE + ".*";
    }

    @Override
    public String getConsumerGroup() {
        return LogService.class.getSimpleName();
    }
}
