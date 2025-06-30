package com.jupiter.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2025/7/1
 */
@Slf4j
@Configuration
public class ChatClientConfig {

    @Bean
    public ChatMemory chatMemory() {
        log.info("Initializing ChatMemory with a maximum of 20 messages.");
        return MessageWindowChatMemory.builder()
                .maxMessages(20)
                .build();
    }
}
