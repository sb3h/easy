package com.easy.json.gson;

import com.easy.json.IJsonDriver;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GsonUtil implements IJsonDriver {

    private Gson mGson;

    public GsonUtil() {
        mGson = new Gson();
    }

    @Override
    public String objectToJson(Object obj) {
        return mGson.toJson(obj);
    }

    @Override
    public <T> T jsonToObject(String json, Class<T> classOfT) {
        return mGson.fromJson(json, classOfT);
    }

    @Override
    public <T> ArrayList<T> jsonToList(String json, Class<T> classOfT) {
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjs = mGson.fromJson(json, type);

        ArrayList<T> listOfT = new ArrayList<T>();
        for (JsonObject jsonObj : jsonObjs)
            listOfT.add(mGson.fromJson(jsonObj, classOfT));

        return listOfT;
    }

}
