package com.cjk.domain;

public class TestVO {

    public TestVO(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public String name;

    public String sex;

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
}
