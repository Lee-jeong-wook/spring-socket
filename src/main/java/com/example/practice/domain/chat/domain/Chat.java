package com.example.practice.domain.chat.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 자동 생성되는 ID

    private String userId;
    private String msg;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
