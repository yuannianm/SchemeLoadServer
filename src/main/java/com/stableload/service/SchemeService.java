package com.stableload.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class SchemeService {
    public JSONArray getScheme(){
        JSONArray tree=new JSONArray();
        JSONObject object=new JSONObject();
        object.put("test","vl");
        tree.add(object);
        //TODO: 这是2.2显示方案,返回树形JSON数组
        return tree;
    }
    public boolean storeScheme(JSONObject scheme){
        //TODO:这是2.1.5存入数据库,返回true保存成功
        return false;
    }
}
