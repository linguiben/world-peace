package com.jupiter.admin.controllor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @desc: Controller for handling Vue.js related requests.
 * @author: Jupiter.Lin
 * @date: 2025/7/26
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class VueController {

    @GetMapping(value = {"/vue", "/vue/**"})
    public String vue() {
        return "forward:/vueDemo1/testVue.html";
    }

    @GetMapping(value = {"/shop"})
    public String shop() {
        return "shop/shop";
    }

    @GetMapping(value = {"/pyda"})
    public String pyda() {
        return "forward:/pyda/pyda.html";
    }

    @GetMapping(value = {"/pyda2"})
    public String pyda2() {
        return "forward:/pyda/pyda2.html";
    }
}