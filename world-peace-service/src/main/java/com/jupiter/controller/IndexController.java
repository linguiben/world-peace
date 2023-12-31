package com.jupiter.controller;

import com.jupiter.service.MapTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
* @Description:
* @author: Jupiter.Lin
* @version: V1.0 
* @date: 2021年6月3日 下午3:31:40
*/
@RequestMapping("/jupiter")
@RestController
public class IndexController {

    @Value("${project.name}")
    private String projectName;

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


    @Autowired
    private MapTest mapTest;

    @RequestMapping("/mapTest")
    public MapTest mapTest(){
        return this.mapTest;
    }

    @RequestMapping("/project")
    public String getProjectName(){
        return this.projectName;
    }
}
