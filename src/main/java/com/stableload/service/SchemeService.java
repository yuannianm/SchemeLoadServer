package com.stableload.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stableload.Mapper.QuotaMapper;
import com.stableload.Mapper.SchemeMapper;
import com.stableload.Mapper.ScoringStandardMapper;
import com.stableload.model.Quota;
import com.stableload.model.Scheme;
import com.stableload.model.ScoringStandard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Service
public class SchemeService {
    @Autowired
    private SchemeMapper schemeMapper;
    @Autowired
    private QuotaMapper quotaMapper;
    @Autowired
    private ScoringStandardMapper scoringstandardMapper;

    public JSONArray getScheme(){
        JSONArray tree = new JSONArray();
        for (Scheme e : schemeMapper.getAllCases()
        ) {
            JSONObject object=addLabel(e.getName(),new JSONObject());
            tree.add(object);
            addChild(e.getId(), object);
        }
        return tree;
    }
    @Transactional
    public boolean storeScheme(JSONObject scheme) throws Exception{
        int i,j;
        String schemename =(String) scheme.get("schemeName");
        //用于存放方案、指标和评分标准的ID，便于后续取父ID

        //填充表格NULL
        Object obj = scheme.get("body");
        ArrayList<ArrayList<String>> obj2= fillChart(obj);

        Map<String,String> map=addScheme(schemename); //处理方案
        // if (map.isEmpty()) return false; //重名

        //进行指标和评分标准的处理
        for(j=0;j<((ArrayList<ArrayList<String>>) obj).size();j++) {
            ArrayList<String> obj3 = obj2.get(j);
            int count=0;//每遍历Excel的一行就会重置为0，用于计算指标等级
            for (i = 0; i < obj3.size(); i++) {
                count++;
                if (i!=obj3.size()-1) {//不是每一行的最后一个，说明是指标
                    if (!map.containsKey(obj3.get(i))){
                        Quota quota = new Quota();
                        quota.setId(getUUID());
                        quota.setName(obj3.get(i));
                        quota.setGrade(count);
                        if (i != 0) {//不是这行的第一个指标，则父ID为前一格代表的方案ID
                            quota.setParent_id(map.get(obj3.get(i - 1)));
                        } else {//是这行的第一个指标，说明是一级指标，父ID为方案ID
                            quota.setParent_id(map.get(schemename));
                        }
                        map.put(quota.getName(), quota.getId());
                        quotaMapper.addQuota(quota);
                    }
                }
                else  {//是每一行的最后一个，说明是评分标准
                    ScoringStandard scoringStandard=new ScoringStandard();
                    scoringStandard.setId(getUUID());
                    scoringStandard.setName(obj3.get(i));
                    scoringStandard.setParent_id(map.get(obj3.get(i-1)));
                    scoringstandardMapper.addScoringStandard(scoringStandard);
                }
            }
        }
        return true;
    }


    private JSONObject addLabel(String label,JSONObject obj){
        obj.put("label",label);
        return obj;
    }
    private void addChild(String id, JSONObject data) {
        //层序遍历获取,与getScheme配合使用
        JSONArray children = new JSONArray();
        for (Quota q : quotaMapper.getCaseBypreId(id)) {
            JSONObject obj=addLabel(q.getName(),new JSONObject());
            addChild(q.getId(), obj);
            children.add(obj);
        }
        if (quotaMapper.getCaseBypreId(id).isEmpty()) {//保证叶节点
            for (ScoringStandard s : scoringstandardMapper.getCaseBypreId(id)
            ) {
                JSONObject obj=addLabel(s.getName(),new JSONObject());
                children.add(obj);
            }
        }
        data.put("children", children);
    }
    private static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        return str+","+temp;
    }
    private ArrayList<ArrayList<String>> fillChart(Object obj){
        ArrayList<ArrayList<String>> obj2= (ArrayList<ArrayList<String>>) obj;
        //对数组进行处理，将null的部分进行填充
        for(int j=0;j<((ArrayList<ArrayList<String>>) obj).size();j++) {
            ArrayList<String> obj3 = obj2.get(j);
            for (int i = 0; i < obj3.size(); i++) {
                if(obj3.get(i)==null) {
                    obj3.set(i,obj2.get(j-1).get(i));
                }
            }
        }
        return obj2;
    }
    private Map addScheme(String schemename) throws Exception{
        Map<String, String> map = new HashMap<String,String>();
        Scheme scheme2=new Scheme();
        scheme2.setId(getUUID());//用UUID作为方案的ID，指标和评分标准同
        scheme2.setName(schemename);
        if(schemeMapper.getSchemeByName(scheme2.getName())==null) {//判断方案有无重名
            map.put(scheme2.getName(), scheme2.getId());//将方案的ID放入map中
            schemeMapper.addScheme(scheme2);
        } else {
            throw new RuntimeException();
        }
        return  map;

    }

}
