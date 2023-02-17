package com.kafka.producer.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.producer.config.KafkaProducerConfig;
import com.kafka.producer.vo.UserVo;

@RestController
@RequestMapping("kafka")
public class ProducerController {

	 private static final Logger LOGGER = LogManager.getLogger(ProducerController.class);

	    @Autowired
	    private KafkaTemplate<String, UserVo> kafkaTemplate;

	    @GetMapping("/publish/{dpt}/{id}")
	    public String post(@PathVariable("dpt") final String dpt, @PathVariable("id") final String id) {

	        UserVo userVo = new UserVo(id, dpt);

	        kafkaTemplate.send(KafkaProducerConfig.JSON_TOPIC, userVo);


	        return "Published done";
	    }
	
}
