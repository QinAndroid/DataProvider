package com.qzk.library.entitys;

import com.qzk.library.enums.QuerySortType;

/**
 * 类名：QuerySortType
 * 描述：排序
 * 包名： com.qzk.library.query.sort
 * 项目名：DataProvider
 * Created by qinzongke on 6/23/16.
 */
public class QuerySort {

    private String fieldsName;
    private QuerySortType type;

    public QuerySort(String fieldsName, QuerySortType type) {
        this.fieldsName = fieldsName;
        this.type = type;
    }

    public String getFieldsName() {
        return fieldsName;
    }

    public void setFieldsName(String fieldsName) {
        this.fieldsName = fieldsName;
    }

    public QuerySortType getType() {
        return type;
    }

    public void setType(QuerySortType type) {
        this.type = type;
    }
}
