package com.bpcbt.services;

import com.bpcbt.configs.CustomStompSessionHandler;
import com.bpcbt.configs.CustomStompSessionHandler2;
import com.bpcbt.model.Message;
import com.bpcbt.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerServiceImpl implements ServerService {

    @Autowired
    private CustomStompSessionHandler2 stompSession;

    @Override
    public void sendMessage(String path, Message payload) {
        stompSession.getStompSession().send(path, payload);
    }

    @Override
    public void sendMessage(String path, org.springframework.messaging.Message<Request> payload) {
        stompSession.getStompSession().send(path, payload);
    }

    @Override
    public void sendMessage(String path, Request request) {
        stompSession.getStompSession().send(path, request);
    }
}
