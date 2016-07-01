package com.qzk.library.entitys;

/**
 * 类名：KeyValue
 * 描述：
 * 包名： com.qzk.library.entitys
 * 项目名：DataProvider
 * Created by qinzongke on 6/23/16.
 */
public class KeyValue {

    private String key;
    private Object object;

    public KeyValue(String key, Object object) {
        this.key = key;
        this.object = object;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
