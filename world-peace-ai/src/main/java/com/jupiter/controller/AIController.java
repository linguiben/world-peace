package com.jupiter.controller;

import io.modelcontextprotocol.client.McpSyncClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2025/6/16
 */
@Slf4j
@Controller
public class AIController {
    @Autowired
    private List<McpSyncClient> mcpSyncClients;  // For sync client

//    @Autowired
//    private List<McpAsyncClient> mcpAsyncClients;  // For async client

    /**
     * 获取所有的异步客户端
     * @return 异步客户端列表
     */
    @RequestMapping("/ai/clients")
    @ResponseBody
    public List<McpSyncClient> getMcpSyncClients() {
        log.info("Retrieving all MCP sync clients: {}", mcpSyncClients);
        for (McpSyncClient mcpSyncClient : mcpSyncClients) {
            log.info("MCP Sync Client: {}", mcpSyncClient.getClientInfo());

        }
        return mcpSyncClients;
    }

    /** The Constructor for AIController.
     * AI聊天接口
     * @param userInput 用户输入的消息
     * @return AI生成的回复
     * @throws Exception 异常
     */
    private final ChatClient chatClient;
    public AIController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/ai")
    @ResponseBody
    String generation(@RequestParam(value = "message", required = false) String userInput) throws Exception {
        if(userInput == null || userInput.isEmpty()) {
            return "usage: /ai?message=your_message_here";
        }
        return this.chatClient.prompt()
                .user(userInput)
                .call()
                .content();

    }
}
