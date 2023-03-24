package com.messagingstompwebsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
@ComponentScan({"com.kafka.producer" + "," + "com.kafka.consumer"
 + "," + "com.kafka.producer.vo"})
public class WbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WbsApplication.class, args);
    }
}
