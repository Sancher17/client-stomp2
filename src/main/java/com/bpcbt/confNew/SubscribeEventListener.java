package com.bpcbt.confNew;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Slf4j
@Component
public class SubscribeEventListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent sessionSubscribeEvent) {
        SessionSubscribeEvent sessionSubscribeEvent1 = (SessionSubscribeEvent) sessionSubscribeEvent;
        String simpSessionId = sessionSubscribeEvent1.getMessage().getHeaders().get("simpSessionId").toString();
        String simpDestination = sessionSubscribeEvent1.getMessage().getHeaders().get("simpDestination").toString();
        log.info("simpSessionId {}", simpSessionId );
        log.info("simpDestination {}", simpDestination);
    }
}
