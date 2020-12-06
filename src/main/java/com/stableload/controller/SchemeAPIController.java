package com.stableload.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stableload.service.SchemeService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 方案API接口
 * 接收方案和返回方案
**/

@RestController
public class SchemeAPIController {
    @GetMapping("/api/getscheme")
    //获取方案,返回方案JSON数组
    @CrossOrigin
    public JSONArray getscheme(){
        SchemeService schemeService=new SchemeService();
        return schemeService.getScheme();
    }


    @PostMapping("/api/sendscheme")
    //接收前端来的方案,返回1表示接收成功
    @CrossOrigin
    public boolean sendscheme(@RequestBody JSONObject scheme){
        SchemeService schemeService=new SchemeService();
        return schemeService.storeScheme(scheme);
    }
}
