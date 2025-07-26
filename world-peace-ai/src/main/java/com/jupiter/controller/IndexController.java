package com.jupiter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2025/7/5
 */
@Slf4j
@Controller
public class IndexController {
    @GetMapping("/ai/chat")
    public String chatUI() {
        return "chat";
    }
}
