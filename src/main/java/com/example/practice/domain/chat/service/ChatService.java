package com.example.practice.domain.chat.service;

import com.example.practice.domain.chat.domain.Chat;
import com.example.practice.domain.chat.repository.ChatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    @Transactional
    public Chat saveChat(Chat chat) {
        return chatRepository.save(chat);
    }
}
