package com.jupiter;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.HttpClientSseClientTransport;
import io.modelcontextprotocol.spec.McpSchema;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2025/6/16
 */
public class test {

    @Test
    public void testSSErver() {
        HttpClientSseClientTransport transport = HttpClientSseClientTransport.builder("http://127.0.0.1:8000")
                .build();
        McpSyncClient client = McpClient.sync(transport).build();

        client.initialize();
        System.out.println(client.listTools());

        McpSchema.CallToolResult callToolResult = client.callTool(new McpSchema.CallToolRequest("webpage_capture",
                Map.of("url", "http://www.baidu.com")));
        System.out.println(callToolResult);

        client.closeGracefully();
    }
}
