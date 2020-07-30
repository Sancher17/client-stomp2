package com.bpcbt.services;

import com.bpcbt.model.Message;
import com.bpcbt.model.Request;

public interface ServerService {

    void sendMessage(String path, Message payload);
    void sendMessage(String path, org.springframework.messaging.Message<Request> payload);
    void sendMessage(String path, Request request);
}
