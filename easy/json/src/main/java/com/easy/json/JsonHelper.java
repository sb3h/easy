package com.easy.json;

import com.easy.json.fastjson.FastJsonUtil;

import java.util.ArrayList;

public class JsonHelper {

    private static IJsonDriver mJsonDriver;

    static {
        mJsonDriver = new FastJsonUtil();
    }

    public static void setJsonDriver(IJsonDriver jsonDriver) {
        mJsonDriver = jsonDriver;
    }

    /**
     * 将一个对象转化成json数据
     *
     * @param obj 与json数据相对应的java类对象
     * @return
     */
    public static String objectToJson(Object obj) {
        return mJsonDriver.objectToJson(obj);
    }

    /**
     * 将json数据转化成一个对象
     *
     * @param json     json数据
     * @param classOfT 与json数据相对应的java类
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String json, Class<T> classOfT) {
        return mJsonDriver.jsonToObject(json, classOfT);
    }

    /**
     * 将json数据转化成一个对象集合
     *
     * @param json     json数据
     * @param classOfT 与json数据相对应的java类
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> jsonToList(String json, Class<T> classOfT) {
        return mJsonDriver.jsonToList(json, classOfT);
    }

}
