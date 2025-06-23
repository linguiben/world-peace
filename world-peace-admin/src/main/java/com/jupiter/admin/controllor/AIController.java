package com.jupiter.admin.controllor;

import com.jupiter.admin.bean.McpCall;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
//    private List<McpSyncClient> mcpSyncClients;  // For sync client

//    @Autowired
//    private List<McpAsyncClient> mcpAsyncClients;  // For async client

    @Value("${mcp.server.url}")
    private String mcpServerUrl;  // MCP server URL, e.g., "http://JupiterSo.com:8000"
    /**
     * 获取所有的异步客户端
     *
     * @return 异步客户端列表
     */
//    @RequestMapping("/ai/clients")
//    @ResponseBody
//    public List<McpSyncClient> getMcpSyncClients() {
//        log.info("Retrieving all MCP sync clients: {}", mcpSyncClients);
//        for (McpSyncClient mcpSyncClient : mcpSyncClients) {
//            log.info("MCP Sync Client: {}", mcpSyncClient.getClientInfo());
//        }
//        return mcpSyncClients;
//    }

    @RequestMapping("/ai/mcp")
    public String mcp() {
        return "mcp";
    }

    /**
     * 通过HttpClient发送如下curl的http请求：
     * curl -i -X POST http://localhost:8080/mcp/
     * -H "Content-Type: application/json"
     * -H "Accept: application/json, text/event-stream"
     * -d '{"jsonrpc":"2.0","id":1,"method":"tools/list","params":{}}'
     */
    @RequestMapping("/ai/mcp/listTools")
    @ResponseBody
    public String mcpListTools() {
        McpCall mcpCall = new McpCall();
        String json = mcpCall.toJson();
        log.info("MCP Call: {}", json);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(mcpServerUrl+"/mcp/"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json, text/event-stream")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        String result = "";
        try {
//            System.out.println("Request URI: " + request.uri());
//            System.out.println("Request Method: " + request.method());
//            System.out.println("HTTP Proxy: " + System.getProperty("http.proxyHost"));
//            System.out.println("HTTPS Proxy: " + System.getProperty("https.proxyHost"));
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println("Response Code: " + response.statusCode());
            result = response.body();
            log.info("mcp Response {}" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            client.close();
        }
        return result;
    }

    @RequestMapping("/ai/mcp/callTool")
    @ResponseBody
    public String mcpCallToll(@RequestParam String toolName, @RequestParam Map<String, String> params) {
        McpCall mcpCall = new McpCall();
        mcpCall.setMethod("tools/call");
        mcpCall.setParams(Map.of("name",toolName,"arguments", params));
        String json = mcpCall.toJson();
        log.info("MCP Call: {}", json);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(mcpServerUrl+"/mcp/"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json, text/event-stream")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        String result = "";
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            result = response.body();
            log.info("mcp Response {}" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            client.close();
        }
        return result;
    }

//    @RequestMapping("/ai/sse/listTools")
//    @ResponseBody
//    public List<String> listTools() { // @RequestParam(defaultValue = "#{spring.ai.mcp.client.sse.connections.server1
//        // .url}") String url
//        // 参考文档 https://modelcontextprotocol.io/sdk/java/mcp-client#model-context-protocol-client
//        HttpClientSseClientTransport transport = HttpClientSseClientTransport
//                .builder(mcpServerUrl)
//                .build();
//        McpSyncClient client = McpClient.sync(transport)
//                .loggingConsumer(notification ->
//                    log.info("Received log message: {}", notification.data())
//                ).build();
//        client.initialize();
//        McpSchema.ListToolsResult tools = client.listTools();
//        log.info("List of tools: {}", tools);
//        // get tools names as a  list
//        List<String> toolNames = tools.tools().stream().map(tool -> tool.name()).toList();
//        client.closeGracefully();
//        return toolNames;
//    }

//    @RequestMapping("/ai/sse/capture")
//    @ResponseBody
//    public String triggerUrlCapture(@RequestParam String url) {
//        HttpClientSseClientTransport transport = HttpClientSseClientTransport.builder(mcpServerUrl)
//                .build();
//        McpSyncClient client = McpClient.sync(transport)
//                .loggingConsumer(notification ->
//                    log.info("Received log message: {}", notification.data())
//                ).build();
//
//        client.initialize();
//        System.out.println(client.listTools());
//
//        McpSchema.CallToolResult callToolResult = client.callTool(new McpSchema.CallToolRequest("webpage_capture",
//                Map.of("url", url)));
//        log.info("Tool call result: {}", callToolResult);
//
//        client.closeGracefully();
//
//        return callToolResult.toString();
//    }
//
//    @RequestMapping("/ai/sse/callTool")
//    @ResponseBody
//    public String callTool(@RequestParam String toolName, @RequestParam Map<String, Object> parameters) {
//        HttpClientSseClientTransport transport = HttpClientSseClientTransport.builder(mcpServerUrl)
//                .build();
//        McpSyncClient client = McpClient.sync(transport)
//                .loggingConsumer(notification ->
//                        log.info("Received log message: {}", notification.data())
//                ).build();
//        client.initialize();
//
//
//        log.info("toolName: {}, parameters: {}", toolName, parameters);
//
//        McpSchema.CallToolResult callToolResult = client.callTool(new McpSchema.CallToolRequest(toolName, parameters));
//        log.info("Tool call result: {}", callToolResult);
//
//        client.closeGracefully();
//
//        return callToolResult.toString();
//    }
}
