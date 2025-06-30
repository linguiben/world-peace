package com.jupiter.controller;

import io.modelcontextprotocol.client.McpSyncClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @RequestMapping("/ai")
    @ResponseBody
    public List<McpSyncClient> getMcpSyncClients() {
        log.info("Retrieving all MCP sync clients: {}", mcpSyncClients);
        for (McpSyncClient mcpSyncClient : mcpSyncClients) {
            log.info("MCP Sync Client: {}", mcpSyncClient.getClientInfo());

        }


        return mcpSyncClients;
    }
}
