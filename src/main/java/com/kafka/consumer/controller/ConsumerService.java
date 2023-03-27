package com.kafka.consumer.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.consumer.config.KafkaConsumerConfig;
import com.kafka.producer.vo.UserVo;

@Service
public class ConsumerService {

	private static final Logger LOGGER = LogManager.getLogger(ConsumerService.class);
	
	@KafkaListener(topics = KafkaConsumerConfig.TEST_TOPIC, groupId = KafkaConsumerConfig.GROUP_1)
	public void consume(String message) {
		LOGGER.info("Consumed message: {} ", message);
	}
	
	
	@KafkaListener(topics = KafkaConsumerConfig.JSON_TOPIC, groupId = KafkaConsumerConfig.GROUP_2,
			containerFactory = "userKafkaListenerFactory")
	public void consumeJson(UserVo user) throws InterruptedException {
		LOGGER.info("Consumed JSON Message: {} ", "id: " + user.getId() + " ; dpt: " +  user.getDpt());
	}

}
