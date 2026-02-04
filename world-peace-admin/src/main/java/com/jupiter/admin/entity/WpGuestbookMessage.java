package com.jupiter.admin.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WpGuestbookMessage {

    private Long id;
    private String username;
    private String content;
    private String ip;
    private LocalDateTime createdAt;
}
