package Lookids.Feed.common.config;

import Lookids.Feed.feed.dto.in.FeedKafkaDto;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.support.serializer.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.common.serialization.StringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@EnableKafka
@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    public static class KafkaJsonSerializer<T> extends JsonSerializer<T> {

        private final ObjectMapper objectMapper;

        public KafkaJsonSerializer() {
            this.objectMapper = new ObjectMapper();
            this.objectMapper.registerModule(new JavaTimeModule());
        }

        @Override
        public byte[] serialize(String topic, T value) {
            try {
                return objectMapper.writeValueAsBytes(value);
            } catch (IOException e) {
                log.error("Error serializing object to JSON", e);
                throw new RuntimeException("Error serializing object to JSON", e);
            }
        }
    }

    @Bean
    public Map<String, Object> feedProducerConfigs() {
        Map<String, Object> producerProps = new HashMap<>();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSerializer.class);
        return producerProps;
    }

    @Bean
    public ProducerFactory<String, FeedKafkaDto> createFeedNotification() {
        return new DefaultKafkaProducerFactory<>(feedProducerConfigs());
    }

    @Bean
    public KafkaTemplate<String, FeedKafkaDto> kafkaTemplate() {
        return new KafkaTemplate<>(createFeedNotification());
    }

}