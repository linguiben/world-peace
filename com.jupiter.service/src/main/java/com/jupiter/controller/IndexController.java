package com.jupiter.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @Description:
* @author: Jupiter.Lin
* @version: V1.0 
* @date: 2021年6月3日 下午3:31:40
*/
@RequestMapping("/jupiter")
@RestController
public class IndexController {
    // @ResponseBody //以jason格式返回
    @RequestMapping("/index")
    public String index() {
        return "success";
    }
    // @ResponseBody
    @RequestMapping("/listIndex")
    public List<String> listIndex() {
        List<String> list = new ArrayList<>();
        list.add("hellow");
        list.add("world");
        return list;
    }
    @RequestMapping("/demoError")
    public Integer demoError(HttpServletRequest request) {
        String id = request.getParameter("id");
        int id1 = Integer.parseInt(id);
        return id1;
    }
}
