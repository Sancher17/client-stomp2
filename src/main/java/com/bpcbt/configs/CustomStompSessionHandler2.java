package com.bpcbt.configs;

import com.bpcbt.model.Request;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Setter
@Getter
@Component
public class CustomStompSessionHandler2 extends StompSessionHandlerAdapter {

    private Logger log = LogManager.getLogger(CustomStompSessionHandler2.class);

    private StompSession stompSession;

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        this.stompSession = session;
        log.info("New session established HANDLER 2: " + session.getSessionId());
        session.subscribe("/topic/greetings", this);
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Message msg = (Message) payload;
        log.info("Header MessageID: " + headers.getMessageId());
        log.info("Header Subscription: " + headers.getSubscription());
        log.info("Header Destination: " + headers.getDestination());
        log.info("Received : " + msg.getPayload());
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.error("Got an exception", exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Message.class;
    }
}
