package com.csanbar.stock_manager_consumer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;


import java.util.HashMap;
import java.util.Map;

public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();

        String bootstrapAdress = "localhost:29092";

        configProps.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAdress
        );
        configProps.put(
                JsonSerializer.TYPE_MAPPINGS, "com.csanbar.stock_manager_consumer.events.Event"
        );

        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(),  new JsonDeserializer<>());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
