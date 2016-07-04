package com.easy.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.easy.json.IJsonDriver;

import java.util.ArrayList;

public class FastJsonUtil implements IJsonDriver {

    @Override
    public String objectToJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    @Override
    public <T> T jsonToObject(String json, Class<T> classOfT) {
        return JSON.parseObject(json, classOfT);
    }

    @Override
    public <T> ArrayList<T> jsonToList(String json, Class<T> classOfT) {
        return (ArrayList<T>) JSON.parseArray(json, classOfT);
    }

}
