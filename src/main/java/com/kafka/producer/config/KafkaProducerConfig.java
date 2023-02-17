package com.kafka.producer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.kafka.producer.vo.UserVo;

@Configuration
public class KafkaProducerConfig {
	
	//yml設定時備用
	//@Value("${spring.kafka.bootstrap-servers}")
	//private String bootstrapServers;
	public static final String JSON_TOPIC = "test";
    public static final String DEFAULT_SERVER = "192.168.51.204:29003";

    @Bean
    public ProducerFactory<String, UserVo> producerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, DEFAULT_SERVER);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, UserVo> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    /**
     * 自動註冊 KafkaAdmin，自動將我們設定的 NewTopic Bean 新增到 Kafka Server 的 Topic
     * @return
     */
//    @Bean
//    public NewTopic defaultTopic() {
//        return TopicBuilder.name(JSON_TOPIC).build();
//    }

}
