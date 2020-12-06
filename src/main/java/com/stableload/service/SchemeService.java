package com.stableload.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stableload.Mapper.QuotaMapper;
import com.stableload.Mapper.SchemeMapper;
import com.stableload.Mapper.ScoringstandardMapper;
import com.stableload.model.Quota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchemeService {
    @Autowired
    private SchemeMapper schemeMapper;
    private QuotaMapper quotaMapper;
    private ScoringstandardMapper scoringstandardMapper;

    public JSONArray getScheme(){
        JSONArray tree=new JSONArray();
        JSONObject object=new JSONObject();
        object.put("label","vl");
        tree.add(object);
        tree.add(schemeMapper.getAllCases());
        //TODO: 这是2.2显示方案,返回树形JSON数组
        return tree;
    }
    public boolean storeScheme(JSONObject scheme){
        //TODO:这是2.1.5存入数据库,返回true保存成功
        return false;
    }
}
