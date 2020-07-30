package com.bpcbt;

import com.bpcbt.configs.CustomStompSessionHandler;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;

public class StompClient {

    private static String URL = "ws://localhost:8080/websocket";

    public static void main(String[] args) throws InterruptedException {
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
//        stompClient.setTaskScheduler(taskScheduler); потом добавить через автоваред

        StompSessionHandler sessionHandler = new CustomStompSessionHandler();
        stompClient.connect(URL, sessionHandler);

        System.out.println("STOP Client is running: " + stompClient.isRunning());

        new Scanner(System.in).nextLine(); // Don't close immediately.
    }
}
