package com.kafka.producer.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.producer.config.KafkaProducerConfig;
import com.kafka.producer.vo.UserVo;

@Lazy
@RestController
@RequestMapping("kafka")
public class ProducerController {

	 private static final Logger LOGGER = LogManager.getLogger(ProducerController.class);

	 @Autowired
	 private KafkaTemplate<String, UserVo> kafkaTemplate;
	 
	 @Autowired
	 private KafkaTemplate<String, String> messageTemplate;
	 
	 @GetMapping("/publish")
	 public String post() {
		 System.out.println("1234567890............");
		 UserVo userVo = new UserVo("007", "hr");
		 
		 kafkaTemplate.send(KafkaProducerConfig.JSON_TOPIC, userVo);
		 
		 
		 return "Published done";
	 } 
	 
	 @GetMapping("/chkMessage")
	 public String message() {
		 System.out.println("run chkMessage");
		 
		 messageTemplate.send(KafkaProducerConfig.JSON_TOPIC, "test message...");
		 
		 
		 return "Published done";
	 } 
}
