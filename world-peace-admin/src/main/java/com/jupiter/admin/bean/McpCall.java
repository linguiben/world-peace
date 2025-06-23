package com.jupiter.admin.bean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;

import java.util.Map;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2025/6/23
 */
@Data
public class McpCall {
    String jsonrpc = "2.0";
    Integer id = 1;
    String method = "tools/list";
    Map<String, Object> params;

    public static Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    public String toJson (){
        return gson.toJson(this);
    }
}
