package com.qzk.library.entitys;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名：MultiQuerySorts
 * 描述：
 * 包名： com.qzk.library.entitys
 * 项目名：DataProvider
 * Created by qinzongke on 6/30/16.
 */
public class MultiQueryFields {

    private List<KeyValue> keyValues = new ArrayList<>();



    public void add(KeyValue ...values){
        for(KeyValue value : values){
            if(null == value){
                continue;
            }
            keyValues.add(value);
        }
    }

    public List<KeyValue> getKeyValues(){
        return this.keyValues;
    }
}
