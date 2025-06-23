package com.jupiter.admin.controllor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2025/6/23
 */
@ExtendWith(MockitoExtension.class)
class AIControllerTest {

    @Spy
    private AIController aiController;

    @Test
    void testMcpCallToll() {
        String toolName = "tools/list";
        Map<String, String> params = Map.of("_META", "progressToken:2");
        aiController.mcpCallToll(toolName, params);
    }
}