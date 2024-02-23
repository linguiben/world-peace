package com.jupiter.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FirstController {

    @RequestMapping("/")
    public String worldPeace() {
        return "index";
    }

    @ResponseBody
    @RequestMapping("/index")
    public String index() {
        return "世界和平!";
    }

    @RequestMapping("/ws")
    public String websocket() {
        return "websocket";
    }

}
