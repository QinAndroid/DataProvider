package com.qzk.library.querybuilder;

import com.qzk.library.entitys.KeyValue;
import com.qzk.library.entitys.MultiQueryFields;
import com.qzk.library.entitys.MultiQuerySorts;
import com.qzk.library.entitys.QuerySort;

import java.util.List;

/**
 * 类名：FieldQueryBuilder
 * 描述：
 * 包名： com.qzk.library.querybuilder
 * 项目名：DataProvider
 * Created by qinzongke on 6/30/16.
 */
public class FieldQueryBuilder implements IFieldQuery {

    @Override
    public Object findByUniqueIden(Object value, String... selectFields) {
        return null;
    }

    @Override
    public List<Object> findByField(KeyValue value, String... selectFields) {
        return null;
    }

    @Override
    public List<Object> findByField(KeyValue value, QuerySort sort, String... selectFields) {
        return null;
    }

    @Override
    public List<Object> findByField(KeyValue value, MultiQuerySorts sorts, String... selectFields) {
        return null;
    }

    @Override
    public List<Object> findByFields(MultiQueryFields fields, String... selectFields) {
        return null;
    }

    @Override
    public List<Object> findByFields(MultiQueryFields fields, QuerySort sort, String... selectFields) {
        return null;
    }

    @Override
    public List<Object> findByFields(MultiQueryFields fields, MultiQuerySorts sorts, String... selectFields) {
        return null;
    }
}
