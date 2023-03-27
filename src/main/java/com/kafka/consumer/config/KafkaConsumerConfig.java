package com.kafka.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.kafka.producer.vo.UserVo;

@EnableKafka/**Enable Kafka listener annotated **/
@Configuration
public class KafkaConsumerConfig {
	
	public static final String TEST_TOPIC = "test";
    public static final String JSON_TOPIC = "kafkaTest1";
    public static final String DEFAULT_SERVER = "192.168.51.204:29004";
    public static final String GROUP_1 = "group_1";
    public static final String GROUP_2 = "group_2";
    private static final Logger LOGGER = LogManager.getLogger(KafkaConsumerConfig.class);


    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, DEFAULT_SERVER);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_1);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }


	@Bean
    public ConsumerFactory<String, UserVo> userConsumerFactory() {
        Map<String, Object> config = new HashMap<>();
        JsonDeserializer<UserVo> jdz = new JsonDeserializer<>(UserVo.class);
        jdz.addTrustedPackages("com.kafka.producer.vo.UserVo");
        
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, DEFAULT_SERVER);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_2);
        
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, jdz);
//        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), jdz);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserVo> userKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserVo> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userConsumerFactory());
        return factory;
    }
	
    
}
