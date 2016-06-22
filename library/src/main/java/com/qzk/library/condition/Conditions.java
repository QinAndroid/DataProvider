package com.qzk.library.condition;

/**
 * 类名：Conditions
 * 描述：
 * 包名： com.qzk.library.condition
 * 项目名：DataProvider
 * Created by qinzongke on 6/22/16.
 */
public class Conditions {
    private String key;
    private Object value;

    public Conditions(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
