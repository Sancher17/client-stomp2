package com.bpcbt.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Configuration
public class Config {

    @Autowired
    private CustomStompSessionHandler customStompSessionHandler;

    @Autowired
    private CustomStompSessionHandler2 customStompSessionHandler2;

    @Bean
    public WebSocketStompClient stompClient(){
        String URL = "ws://localhost:8080/websocket";
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        stompClient.setTaskScheduler(new ConcurrentTaskScheduler());
        stompClient.connect(URL, customStompSessionHandler);
        return stompClient;
    }

    @Bean
    public WebSocketStompClient stompClient2(){
        String URL = "ws://localhost:8080/websocket";
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        stompClient.setTaskScheduler(new ConcurrentTaskScheduler());
        stompClient.connect(URL, customStompSessionHandler2);
        return stompClient;
    }
}
