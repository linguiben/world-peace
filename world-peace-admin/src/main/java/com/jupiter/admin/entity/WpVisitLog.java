package com.jupiter.admin.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WpVisitLog {
    private Long id;

    private String ip;
    private String path;
    private String method;

    private String userAgent;
    private String browser;

    private String referer;
    private String acceptLanguage;

    private LocalDateTime createdAt;
}
