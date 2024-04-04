/**
 * @className WebScoketControllor
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-04-02 00:22
 */
package com.jupiter.admin.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Jupiter.Lin
 * @desc TODO
 * @date 2024-04-02 00:22
 */
@Controller
public class WebScoketControllor {

    @GetMapping("/jupiter/websocket")
    public String websocket() {
        return "websocket";
    }
}
