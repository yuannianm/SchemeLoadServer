package com.stableload.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stableload.Mapper.QuotaMapper;
import com.stableload.Mapper.SchemeMapper;
import com.stableload.Mapper.ScoringstandardMapper;
import com.stableload.model.Quota;
import com.stableload.model.Scheme;
import com.stableload.model.Scoringstandard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchemeService {
    @Autowired
    private SchemeMapper schemeMapper;
    @Autowired
    private QuotaMapper quotaMapper;
    @Autowired
    private ScoringstandardMapper scoringstandardMapper;

    public JSONArray getScheme() {
        JSONArray tree = new JSONArray();
        for (Scheme e : schemeMapper.getAllCases()
        ) {
            JSONObject object = new JSONObject();
            object.put("label", e.getName());
            tree.add(object);
            addChild(e.getId(), object);
        }
        return tree;
    }


    public boolean storeScheme(JSONObject scheme) {
        //TODO:这是2.1.5存入数据库,返回true保存成功
        return false;
    }

    private void addChild(String id, JSONObject data) {
        JSONArray children = new JSONArray();
        for (Quota q : quotaMapper.getCaseBypreId(id)) {
            JSONObject obj = new JSONObject();
            obj.put("label", q.getBody());
            addChild(q.getId(), obj);
            children.add(obj);
        }
        if (quotaMapper.getCaseBypreId(id).isEmpty()) {//保证叶节点
            for (Scoringstandard s : scoringstandardMapper.getCaseBypreId(id)
            ) {
                JSONObject obj = new JSONObject();
                obj.put("label", s.getBody());
                children.add(obj);
            }
        }
        data.put("children", children);
    }
}