package com.jupiter.admin.controllor;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.HttpClientSseClientTransport;
import io.modelcontextprotocol.spec.McpSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @desc: AIController
 * @author: Jupiter.Lin
 * @date: 2025/6/16
 */
@Slf4j
@Controller
public class AIController {
//    @Autowired
    private List<McpSyncClient> mcpSyncClients;  // For sync client

//    @Autowired
//    private List<McpAsyncClient> mcpAsyncClients;  // For async client

    /**
     * 获取所有的异步客户端
     *
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
    @RequestMapping("/ai/listTools")
    @ResponseBody
    public String listTools() { // @RequestParam(defaultValue = "#{spring.ai.mcp.client.sse.connections.server1
        // .url}") String url
        HttpClientSseClientTransport transport = HttpClientSseClientTransport
                .builder("http://JupiterSo.com:8000")
                .build();
        McpSyncClient client = McpClient.sync(transport).build();
        client.initialize();
        McpSchema.ListToolsResult listToolsResult = client.listTools();
        log.info("List of tools: {}", listToolsResult);

        return listToolsResult.toString();
    }

    @RequestMapping("/ai/capture")
    @ResponseBody
    public String triggerUrlCapture(@RequestParam String url) {
        HttpClientSseClientTransport transport = HttpClientSseClientTransport.builder("http://JupiterSo.com:8000")
                .build();
        McpSyncClient client = McpClient.sync(transport).build();

        client.initialize();
        System.out.println(client.listTools());

        McpSchema.CallToolResult callToolResult = client.callTool(new McpSchema.CallToolRequest("webpage_capture",
                Map.of("url", url)));
        log.info("Tool call result: {}", callToolResult);

        client.closeGracefully();

        return callToolResult.toString();
    }
}
