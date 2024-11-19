package Lookids.Feed.common.config;

import Lookids.Feed.feed.dto.in.FeedKafkaDto;
import Lookids.Feed.feed.dto.in.UserKafkaDto;
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
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
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

    @Bean
    public Map<String, Object> userProducerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, UserKafkaDto> createUserProfileNotification() {
        return new DefaultKafkaProducerFactory<>(userProducerConfigs());
    }

    @Bean
    public KafkaTemplate<String, UserKafkaDto> userKafkaTemplate() {
        return new KafkaTemplate<>(createUserProfileNotification());
    }
}