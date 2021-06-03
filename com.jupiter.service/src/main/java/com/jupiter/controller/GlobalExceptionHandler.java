package com.jupiter.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* @Description:
* @author: Jupiter.Lin
* @version: V1.0 
* @date: 2021年6月3日 下午5:12:09
*/
@ControllerAdvice //1.全局异常处理 2.全局数据绑定 3.全局数据预处理
public class GlobalExceptionHandler {
    /** @describe 捕获全局异常 */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String,Object> exceptionHandler() { //应该使用mode&view
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("error", "500");
        result.put("errorMsg", "系统错误");
        return result;
    }
}
