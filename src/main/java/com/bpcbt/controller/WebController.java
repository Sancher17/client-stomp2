package com.bpcbt.controller;

import com.bpcbt.configs.CustomStompSessionHandler;
import com.bpcbt.model.Message;
import com.bpcbt.model.Request;
import com.bpcbt.services.ServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/")
@Controller
public class WebController {

//    @Autowired
//    @Qualifier("stompClient")
//    private WebSocketStompClient client;

    @Autowired
    private ServerService serverService;

    @Autowired
    private CustomStompSessionHandler customStompSessionHandler;


    @PostMapping(value = "merchant/qrc-data")
    public String qrcData(@RequestBody Request request) throws Exception {
        log.info("Request: {}", request);
        org.springframework.messaging.Message<Request> build = MessageBuilder.withPayload(request)
//                .setHeader("http_statusCode",HttpStatus.OK)
                .build();
//        serverService.sendMessage("app/merchant/qrc-data", request);
        serverService.sendMessage("app/hello", request);
        return "Данные получены";
    }

    @GetMapping("{name}")
    public String test(@PathVariable String name){
        if (customStompSessionHandler.getStompSession() != null){
            Message testMessage = getTestMessage();
            testMessage.setFrom(name);
            Request request = new Request();
//            serverService.sendMessage("/app/hello", testMessage);
//            serverService.sendMessage("/app/hello1", testMessage);
            serverService.sendMessage("/app/hello1", request);
//            StompSession stompSession = customStompSessionHandler.getStompSession();
//            stompSession.send("/app/hello", getTestMessage());
            return "OK " + name;
        }
        return "BAD, Session is NULL";
    }

    private Message getTestMessage() {
        Message msg = new Message();
        msg.setFrom("Nicky NEW");
        msg.setText("Howdy!! NEW");
        return msg;
    }
}
