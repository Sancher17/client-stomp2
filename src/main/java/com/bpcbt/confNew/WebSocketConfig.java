package com.bpcbt.confNew;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Slf4j
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic/greetings");
//		config.setApplicationDestinationPrefixes("/alex");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		log.info("Connected");
		registry.addEndpoint("/websocket")
				.addInterceptors(new HttpHandshakeInterceptor())
				.setAllowedOrigins("*");
	}
}
