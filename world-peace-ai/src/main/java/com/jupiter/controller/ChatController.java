package com.jupiter.controller;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.jupiter.service.GeminiResponseDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2025/6/29
 */
@Slf4j
@RestController
@CrossOrigin
public class ChatController {

//    @Autowired
//    private ToolCallbackProvider mcpTools;

    //    private OpenAiChatModel openAiChatModel;
    private final ChatMemory chatMemory; // default: InMemoryChatMemory
    private final ChatModel vertexAiGeminiChatModel;
    private final ChatClient vertexChatClient; // ChatClient是比ChatModel更高级的抽象，提供了更多功能，如流式响应、工具调用等。

    /**
     * Constructor for ChatController.
     *
     * @param vertexAiProjectId
     * @param vertexAiLocation
     * @param vertexAiModel
     * @param mcpTools
     * @param chatMemory
     */
    @Autowired
    public ChatController(@Value("${spring.ai.vertex.ai.gemini.project-id}") String vertexAiProjectId,
                          @Value("${spring.ai.vertex.ai.gemini.location:global}") String vertexAiLocation,
                          @Value("${spring.ai.vertex.ai.gemini.model:GEMINI_2_0_FLASH}") String vertexAiModel,
                          ToolCallbackProvider mcpTools,
                          ChatMemory chatMemory) {

        vertexAiProjectId = vertexAiProjectId;
        vertexAiLocation = vertexAiLocation;
        vertexAiModel = vertexAiModel;
        this.chatMemory = chatMemory;
//        VertexAiGeminiChatModel vertexAiGeminiChatModel = new VertexAiGeminiChatModel(vertexAi,
//                VertexAiGeminiChatOptions.builder()
//                        .model(VertexAiGeminiChatModel.ChatModel.GEMINI_1_5_FLASH)
//                        .temperature(0.4)
//                        .build());
        VertexAI vertexAi = new VertexAI(vertexAiProjectId, vertexAiLocation);
        vertexAiGeminiChatModel = VertexAiGeminiChatModel.builder()
                .vertexAI(vertexAi)
                .defaultOptions(VertexAiGeminiChatOptions.builder()
                        .model(vertexAiModel)
                        .temperature(0.4)
                        .build())
                .build();
        vertexChatClient = ChatClient
                .builder(vertexAiGeminiChatModel)
                .defaultSystem("""
                        You are a expert programmer and AI assistant, you can help users to solve their problems.
                        """)
                .defaultAdvisors(PromptChatMemoryAdvisor.builder(chatMemory).build(), new SimpleLoggerAdvisor())
                .defaultTools() // 可以添加默认的工具
                .defaultToolCallbacks(mcpTools) // add mcp server tools
                .build();
    }

    @ResponseBody
    @GetMapping("/ai/testVertexAI")
    public String testGemini(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) throws IOException {
        VertexAI vertexAi = new VertexAI("direct-hope-437005-e1", "global");
        GenerativeModel model = new GenerativeModel("gemini-2.0-flash-001", vertexAi);
        GenerateContentResponse response = model.generateContent(message);
        String decoded = GeminiResponseDecoder.decode(response);
        // 打印生成的内容
        System.out.println("Generated content: " + decoded);
        return decoded;
    }

    @CrossOrigin
    @GetMapping(value = "/ai/vertexChatFlux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> generateStreamAsString(@RequestParam(value = "message", defaultValue = "Tell me a jok") String message) {

        Flux<String> content = vertexChatClient.prompt()
                .system(s -> s.param("current_date", LocalDate.now().toString()))
                //.advisors(a -> a.param(ChatMemory.CONVERSATION_ID, userId))
                .user(message + "/no_think")
//                .advisors(QuestionAnswerAdvisor.builder(vectorStore)
//                        .searchRequest(SearchRequest.builder().similarityThreshold(0.8d).topK(6).build())
//                        .build())
                .stream()
                .content();
        log.info("生成的内容: {}", content);

        return content.concatWith(Flux.just("[complete]"));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/ai/vertexChat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public String generateStreamAsString2(@RequestParam(value = "message", defaultValue = "Tell me a jok") String message) {
        String content = vertexChatClient.prompt()
                .system(s -> s.param("current_date", LocalDate.now().toString()))
                .user(message + "/no_think")
                .stream()
                .content()
                .collectList() // Collect the Flux into a List
                .block() // Block to get the result synchronously
                .stream()
                .reduce("", (acc, item) -> acc + item); // Combine all items into a single String
        log.info("生成的内容: {}", content);
        return content + "[complete]";
    }

    @ResponseBody
    @GetMapping("/ai/generate")
    public Map generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) throws IOException {
        return Map.of("generation", this.vertexAiGeminiChatModel.call(message));
    }

    @GetMapping("/ai/generateStream")
    public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        return this.vertexAiGeminiChatModel.stream(prompt);
    }
}