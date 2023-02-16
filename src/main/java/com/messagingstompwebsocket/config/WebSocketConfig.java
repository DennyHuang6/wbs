package com.messagingstompwebsocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
/**啟動Message broker配置**/
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/ads");//可一到多個
        config.setUserDestinationPrefix("/ads");
//        config.setApplicationDestinationPrefixes("/app");
      //配置RabbitMQ代理
        // 配置支持的topic
//    config.enableStompBrokerRelay("/topic/","/queue/","exchange")
//          .setRelayHost("localhost")   //地址
//          .setRelayPort(61613)     //端口
//          .setClientLogin("guest")  // 账号密码
//          .setClientPasscode("guest")
//          .setVirtualHost("/");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //設定STOMP 訂閱的url
    	registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();

//    	registry.addEndpoint("/publicServer").withSockJS();
//    	registry.addEndpoint("/privateServer").withSockJS();
    }
}