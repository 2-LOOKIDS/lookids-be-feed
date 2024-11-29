package Lookids.Feed.common.config;

import Lookids.Feed.feed.dto.in.FeedKafkaDto;
import Lookids.Feed.feed.dto.in.DeleteKafkaDto;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.support.serializer.JsonSerializer;

import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@EnableKafka
@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    @Bean
    public Map<String, Object> feedProducerConfigs() {
        Map<String, Object> producerProps = new HashMap<>();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return producerProps;
    }

    @Bean
    public ProducerFactory<String, FeedKafkaDto> createFeedNotification() {
        return new DefaultKafkaProducerFactory<>(feedProducerConfigs());
    }

    @Bean
    public KafkaTemplate<String, FeedKafkaDto> feedkafkaTemplate() {
        return new KafkaTemplate<>(createFeedNotification());
    }

    @Bean
    public Map<String, Object> feedDeleteProducerConfigs() {
        Map<String, Object> producerProps = new HashMap<>();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return producerProps;
    }

    @Bean
    public ProducerFactory<String, DeleteKafkaDto> DeleteFeedNotification() {
        return new DefaultKafkaProducerFactory<>(feedProducerConfigs());
    }

    @Bean
    public KafkaTemplate<String, DeleteKafkaDto> deletekafkaTemplate() {
        return new KafkaTemplate<>(DeleteFeedNotification());
    }
}