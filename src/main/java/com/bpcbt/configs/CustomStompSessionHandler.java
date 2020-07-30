package com.bpcbt.configs;

import com.bpcbt.model.Message;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Getter
@Setter
@Component
public class CustomStompSessionHandler extends StompSessionHandlerAdapter {

    private Logger log = LogManager.getLogger(CustomStompSessionHandler.class);

    private StompSession stompSession;

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        this.stompSession = session;
        log.info("New session established : " + session.getSessionId());
        session.subscribe("/topic/greetings", this);
        session.subscribe("/topic/greetings1", this);
        log.info("Subscribed to /topic/messages");
//        session.send("/app/hello", getSampleMessage());
        log.info("Message sent to websocket server");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.error("Got an exception", exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Object.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        org.springframework.messaging.Message<Object> msg = MessageBuilder.withPayload(payload).build();

        log.info("Header ID: " + headers.getId());
        log.info("Header MessageID: " + headers.getMessageId());
        log.info("Header Subscription: " + headers.getSubscription());
        log.info("Header Destination: " + headers.getDestination());
        log.info("Received : " + msg.getPayload());

//        if (destination.equals("/topic/greetings")) {
//            log.info("Header ID: " + headers.getId());
//            log.info("Header MessageID: " + headers.getMessageId());
//            log.info("Header Subscription: " + headers.getSubscription());
//            log.info("Header Destination: " + headers.getDestination());
//            log.info("Header getHeartbeat: " + headers.getHeartbeat());
//            log.info("Header getAck: " + headers.getAck());
//            log.info("Header getHost: " + headers.getHost());
//            log.info("Header getReceipt: " + headers.getReceipt());
//            log.info("Received : " + msg.getText() + " from : " + msg.getFrom());
//            log.info("Received : " + msg.getPayload());
//        }else if (destination.equals("/topic/greetings1")){
//            log.info("Header ID: " + headers.getId());
//            log.info("Header MessageID: " + headers.getMessageId());
//            log.info("Header Subscription: " + headers.getSubscription());
//            log.info("Header Destination: " + headers.getDestination());
//            log.info("Received : " + msg.getText() + " from : " + msg.getFrom());
//        }


        }
    }

//    private Message getSampleMessage() {
//        Message msg = new Message();
//        msg.setFrom("Nicky");
//        msg.setText("Howdy!!");
//        return msg;
//    }


