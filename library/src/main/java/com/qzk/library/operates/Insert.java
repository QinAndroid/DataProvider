package com.qzk.library.operates;

import com.qzk.library.helpers.ObjectHelper;
import com.qzk.library.utils.LogUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 类名：Insert
 * 描述：
 * 包名： com.qzk.library.insert
 * 项目名：DataProvider
 * Created by qinzongke on 6/25/16.
 */
public class Insert {

    private StringBuffer sql = new StringBuffer();
    private static final String INSERT = " insert into ";
    private static final String LEFTBRACKET = "(";
    private static final String RIGHTBACKET = ")";
    private static final String VALUES = "values";
    private static final String COMMA = ",";
    private static final String SQUOTE = "'";//'单引号


    public void insert(Object object){
        String tableName = ObjectHelper.getTableName(object.getClass());
        sql.append(INSERT).append(tableName).append(LEFTBRACKET);
        createKey(object);


    }


    private void createKey(Object object){
        Field[] fields = ObjectHelper.getObjectFields(object.getClass());
        if (null != fields) {
            int len = fields.length;
            Field field = null;
            List<Object> keys = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                field = fields[i];
                if (field.isSynthetic()) {
                    continue;
                }
                if (!ObjectHelper.fieldsIsAutoIncrement(field)) {
                    String fieldName = field.getName();
                    Object value = ObjectHelper.getObjectValue(object,field);
                    keys.add(value);
                    sql.append(fieldName).append(COMMA);
                }
            }
            if(sql.toString().endsWith(COMMA)){
                sql  = new StringBuffer(sql.subSequence(0,sql.length()-1));
            }
            sql.append(RIGHTBACKET).append(VALUES).append(LEFTBRACKET);
            createValues(keys);
        } else {
            LogUtils.e("fields is null or length = 0; ");
        }
    }

    private void createValues(List<Object> values){
        for (Object object : values){
            if(object instanceof String){
                sql.append(SQUOTE).append(object).append(SQUOTE);
            }else{
                sql.append(object);
            }
            sql.append(COMMA);
        }
        if(sql.toString().endsWith(COMMA)){
            sql = new StringBuffer(sql.subSequence(0,sql.length()-1));
        }
        sql.append(RIGHTBACKET);

    }

    public void printf(){
        LogUtils.e("insertPrintf-->"+sql.toString());
    }

    public String createSql(){
        return sql.toString();
    }




}
