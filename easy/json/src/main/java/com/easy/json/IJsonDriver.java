package com.easy.json;

import java.util.ArrayList;

public interface IJsonDriver {

    /**
     * 将一个对象转化成json数据
     *
     * @param obj 与json数据相对应的java类对象
     * @return
     */
    String objectToJson(Object obj);

    /**
     * 将json数据转化成一个对象
     *
     * @param json     json数据
     * @param classOfT 与json数据相对应的java类
     * @param <T>
     * @return
     */
    <T> T jsonToObject(String json, Class<T> classOfT);

    /**
     * 将json数据转化成一个对象集合
     *
     * @param json     json数据
     * @param classOfT 与json数据相对应的java类
     * @param <T>
     * @return
     */
    <T> ArrayList<T> jsonToList(String json, Class<T> classOfT);

}
