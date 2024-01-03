package br.com.store.wholesale.ecommerce.config;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;

import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {
    private final KafkaProperties properties;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaFactory() {
        var configurer = new ConcurrentKafkaListenerContainerFactory();
        configurer.setConsumerFactory(consumerFactory());
        return configurer;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerFactory producerFactory() {
        return new DefaultKafkaProducerFactory<>(properties.buildProducerProperties());
    }

    @Bean
    public ConsumerFactory consumerFactory() {
        return new DefaultKafkaConsumerFactory(properties.buildConsumerProperties());
    }
}
