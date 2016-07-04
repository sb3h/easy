package com.easy.json;

public class ChildBean {

    private String name;
    private String sex;
    private int age;
    private ParentBean[] parentArray;

    public ChildBean() {
    }

    public ChildBean(String name, String sex, int age, ParentBean[] parentArray) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.parentArray = parentArray;
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

    public ParentBean[] getParentArray() {
        return parentArray;
    }

    public void setParentArray(ParentBean[] parentArray) {
        this.parentArray = parentArray;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("【（name=" + name + "，sex=" + sex + "，age=" + age + "）");
        for (int i = 0; parentArray != null && i < parentArray.length; i++) {
            builder.append("[name=" + parentArray[i].getName()
                    + "，sex=" + parentArray[i].getSex()
                    + "，age=" + parentArray[i].getAge() + "]");
        }
        builder.append("】");
        return builder.toString();
    }

}
