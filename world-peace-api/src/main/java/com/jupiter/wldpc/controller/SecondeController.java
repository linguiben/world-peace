package com.jupiter.wldpc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecondeController {

    @RequestMapping("/hello")
    public String hello(){
        return "你好";
    }


}
