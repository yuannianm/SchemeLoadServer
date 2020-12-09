package com.stableload.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stableload.service.SchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 方案API接口
 * 接收方案和返回方案
**/

@RestController
public class SchemeAPIController {
    @Autowired
    SchemeService schemeService;
    @GetMapping("/api/getscheme")
    //获取方案,返回方案JSON数组
    @CrossOrigin
    public JSONArray getscheme(){
        return schemeService.getScheme();
    }


    @PostMapping("/api/sendscheme")
    //接收前端来的方案,返回1表示接收成功
    @CrossOrigin
    public boolean sendscheme(@RequestBody JSONObject scheme) throws Exception{

        return schemeService.storeScheme(scheme);
    }
}
