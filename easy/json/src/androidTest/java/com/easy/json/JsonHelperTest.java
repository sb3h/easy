package com.easy.json;

import android.test.AndroidTestCase;

import com.easy.json.gson.GsonUtil;

import java.util.ArrayList;

public class JsonHelperTest extends AndroidTestCase {

    private static final String LOG_CLASS_NAME = "【JsonHelperTest】";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
//        JsonHelper.setJsonDriver(new FastJsonUtil());
        JsonHelper.setJsonDriver(new GsonUtil());
    }

    public void testObjectToJson() {
        ArrayList<ChildBean> childList = new ArrayList<ChildBean>(1);
        ChildBean childObj = new ChildBean("漩涡鸣人", "男", 1, null);
        childList.add(childObj);
        ArrayList<ParentBean> parentList = new ArrayList<ParentBean>(2);
        ParentBean parentObj1 = new ParentBean("波风水门", "男", 22, childList);
        ParentBean parentObj2 = new ParentBean("辛久奈", "女", 21, childList);
        parentList.add(parentObj1);
        parentList.add(parentObj2);

        String json = JsonHelper.objectToJson(parentList);
        LogCatUtils.log(LOG_CLASS_NAME + "（testObjectToJson）"
                + json);

        ArrayList<ParentBean> list = JsonHelper.jsonToList(json, ParentBean.class);
        LogCatUtils.log(LOG_CLASS_NAME + "（testObjectToJson）"
                + list.toString());
    }

    public void testJsonToObject() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append("\"name\"").append(":").append("\"波风水门\"").append(",");
        builder.append("\"sex\"").append(":").append("\"男\"").append(",");
        builder.append("\"age\"").append(":").append("22").append(",");
        builder.append("\"childList\"").append(":").append("[{");
        builder.append("\"name\"").append(":").append("\"漩涡鸣人\"").append(",");
        builder.append("\"sex\"").append(":").append("\"男\"").append(",");
        builder.append("\"age\"").append(":").append("1").append("}]");
        builder.append("}");

        ParentBean obj = JsonHelper.jsonToObject(builder.toString(), ParentBean.class);
        LogCatUtils.log(LOG_CLASS_NAME + "（testJsonToObject）"
                + obj.toString());
    }

    public void testJsonToList() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append("{").append("\"name\"").append(":").append("\"波风水门\"").append(",");
        builder.append("\"sex\"").append(":").append("\"男\"").append(",");
        builder.append("\"age\"").append(":").append("20").append(",");
        builder.append("\"childList\"").append(":").append("null").append("},");
        builder.append("{").append("\"name\"").append(":").append("\"辛久奈\"").append(",");
        builder.append("\"sex\"").append(":").append("\"女\"").append(",");
        builder.append("\"age\"").append(":").append("19").append("}");
        builder.append("]");

        ArrayList<ParentBean> list = JsonHelper.jsonToList(builder.toString(), ParentBean.class);
        LogCatUtils.log(LOG_CLASS_NAME + "（testJsonToList）"
                + list.toString());
    }

}
