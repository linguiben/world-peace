package com.jupiter.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ParameterController {

    @ResponseBody
    @RequestMapping("/params")
    public String testParameter(String userName, Integer age,
                                @RequestParam("pwd") String password){
        List<String> rsp = new ArrayList();
        rsp.add(userName);
        rsp.add(String.valueOf(age));
        rsp.add(password);
        return rsp.toString();
    }

    @ResponseBody
    @RequestMapping("/params/{username}/{age}")
    public String testParameter1(@PathVariable String username,
                                 @PathVariable Integer age){
        List<String> rsp = new ArrayList();
        rsp.add(username);
        rsp.add(String.valueOf(age));
        return rsp.toString();
    }
}
