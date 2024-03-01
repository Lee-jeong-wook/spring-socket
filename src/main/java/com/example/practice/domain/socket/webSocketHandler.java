package com.example.practice.domain.socket;

import com.example.practice.domain.chat.domain.Chat;
import com.example.practice.domain.chat.service.ChatService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class webSocketHandler extends TextWebSocketHandler {
    private static final ConcurrentHashMap<String, WebSocketSession> CLIENTS = new ConcurrentHashMap<String, WebSocketSession>();
    private final ChatService chatService;

    public webSocketHandler(ChatService chatService) {
        this.chatService = chatService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        CLIENTS.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        CLIENTS.remove(session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Chat chat = new Chat();
        chat.setUserId(session.getId()); // 세션 ID를 사용하여 사용자 식별
        chat.setMsg(message.getPayload());
        chatService.saveChat(chat);
        String id = session.getId();
        CLIENTS.entrySet().forEach( arg->{
            if(!arg.getKey().equals(id)) {
                try {
                    arg.getValue().sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
