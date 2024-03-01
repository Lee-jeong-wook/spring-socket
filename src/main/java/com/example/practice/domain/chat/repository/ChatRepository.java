package com.example.practice.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.practice.domain.chat.domain.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {

}