package com.easy.json;

import java.util.ArrayList;

public class ParentBean {

    private String name;
    private String sex;
    private int age;
    private ArrayList<ChildBean> childList;

    // 必须要有个默认构造方法，否则会抛出以下异常：
    // com.alibaba.fastjson.JSONException: default constructor not found.
    public ParentBean() {
    }

    public ParentBean(String name, String sex, int age, ArrayList<ChildBean> childList) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.childList = childList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<ChildBean> getChildList() {
        return childList;
    }

    public void setChildList(ArrayList<ChildBean> childList) {
        this.childList = childList;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("【[name=" + name + "，sex=" + sex + "，age=" + age + "]");
        if (childList != null && !childList.isEmpty()) {
            for (ChildBean child : childList) {
                builder.append("（name=" + child.getName()
                        + "，sex=" + child.getSex()
                        + "，age=" + child.getAge() + "）");
            }
        }
        builder.append("】");
        return builder.toString();
    }
}
