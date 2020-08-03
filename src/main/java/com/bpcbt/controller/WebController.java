package com.bpcbt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.core.GenericMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;

//import com.bpcbt.configs.CustomStompSessionHandler2;

@Slf4j
@RestController
@RequestMapping("/")
@Controller
public class WebController {

    @Autowired
    WebSocketMessageBrokerStats stats;
//    @Autowired
//    StompSubProtocolHandler stompSubProtocolHandler;
//    @Autowired
//    SubProtocolWebSocketHandler subProtocolWebSocketHandler;
    @Autowired
    WebSocketHandler webSocketHandler;
//    @Autowired WebSocketSession webSocketSession;
    @Autowired SimpMessagingTemplate template;

    @GetMapping()
    @SendTo("/hello1")
    public Message test(){
        log.info("test called");
        log.info(stats.getClientInboundExecutorStatsInfo());
        log.info(stats.getClientOutboundExecutorStatsInfo());
        log.info(stats.getStompBrokerRelayStatsInfo());
        log.info(stats.getStompSubProtocolStatsInfo());
        log.info(stats.getWebSocketSessionStatsInfo());

        Message<String> test = MessageBuilder.withPayload("test").build();
        log.info("message {} ", test);
;
        template.convertAndSend("/topic/greetings", test);
        return test;
    }
//    @Autowired
//    @Qualifier("stompClient")
//    private WebSocketStompClient client;

//    @Autowired
//    private ServerService serverService;
//
//    @Autowired
//    private CustomStompSessionHandler customStompSessionHandler;
////    @Autowired
////    private CustomStompSessionHandler2 customStompSessionHandler2;
//
//
//    @PostMapping(value = "merchant/qrc-data")
//    public String qrcData(@RequestBody Request request)  {
//        log.info("Request: {}", request);
//        Message<Request> build = MessageBuilder.withPayload(request).build();
////        serverService.sendMessage("/hello1", build);
//        serverService.sendMessage("/merchant/qrc-data", build);
//        return "Данные получены";
//    }
//
//    @GetMapping("{name}")
//    public String test(@PathVariable String name){
//        if (customStompSessionHandler.getStompSession() != null){
//            Request request = new Request();
//            serverService.sendMessage("/hello1", request);
//            return "OK " + name;
//        }
//        return "BAD, Session is NULL";
//    }

//    @PostMapping(value = "merchant/qrc-data")
//    public String qrcData(@RequestBody Request request) {
//        if (customStompSessionHandler.getStompSession() != null){
//
//            Message<Request> build = MessageBuilder.withPayload(request).build();
//
//            serverService.sendMessage("/app/hello1", build);
//            return "OK " + " request";
//        }
//        return "BAD, Session is NULL";
//    }
}
