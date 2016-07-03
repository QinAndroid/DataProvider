package com.qzk.library.querybuilder;

import android.database.Cursor;
import android.util.Log;

import com.qzk.library.Exception.ErrorMessageException;
import com.qzk.library.annotations.enums.DataTypes;
import com.qzk.library.entitys.KeyValue;
import com.qzk.library.entitys.MultiQueryFields;
import com.qzk.library.entitys.MultiQuerySorts;
import com.qzk.library.entitys.QuerySort;
import com.qzk.library.helpers.DBHelper;
import com.qzk.library.helpers.ObjectHelper;
import com.qzk.library.utils.LogUtils;
import com.qzk.library.utils.SqlCreateUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 类名：FieldQueryBuilder
 * 描述：
 * 包名： com.qzk.library.querybuilder
 * 项目名：DataProvider
 * Created by qinzongke on 6/30/16.
 */
public class FieldQueryBuilder implements IFieldQuery {

    protected static final String AND = " and ";
    protected static final String EQUAL = "=";
    protected static final String SQUOTE = "'";//'单引号

    private static final String COMMA = ",";//,逗号

    private Class clazz;

    public FieldQueryBuilder(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object findByUniqueIden(Object value, String... selectFields) {
        Field field = ObjectHelper.getPrimaryKeyField(clazz);
        String tableName = ObjectHelper.getTableName(clazz);
        String keyName = field.getName();
        try {
            if (null == field) {
                throw new ErrorMessageException(tableName + "不存在唯一标示，无法进行查询");
            }
        } catch (ErrorMessageException e) {
            e.printStackTrace();
            return null;
        }
        DataTypes types = ObjectHelper.getFieldType(field);
        StringBuffer sql = SqlCreateUtils.createBaseSelectSql(clazz,selectFields);
        try{
            if (value instanceof String && (types.equals(DataTypes.TEXT)||types.equals(DataTypes.VARCHAR))) {
                sql.append(AND).append(keyName).append(EQUAL).append(SQUOTE).append(value).append(SQUOTE);
            } else if ((value instanceof Integer && types.equals(DataTypes.INTEGER)) || (value instanceof Long && types.equals(DataTypes.LONG)) || (value instanceof Float&&types.equals(DataTypes.FLOAT)) || (value instanceof Double)&&types.equals(DataTypes.DOUBLE)) {
                sql.append(AND).append(keyName).append(EQUAL).append(value);
            } else {
                throw new ErrorMessageException("->参数数据类型异常");
            }
        }catch (ErrorMessageException e){
            e.printStackTrace();
            return null;
        }
        LogUtils.e("findByUniqueIden--->"+sql.toString());
        return DBHelper.getInstance().findFist(clazz,sql.toString());
    }

    @Override
    public void updateByUniqueIden(Object value, Object object) {
        Field field = ObjectHelper.getPrimaryKeyField(clazz);
        String tableName = ObjectHelper.getTableName(clazz);
        String keyName = field.getName();
        try {
            if (null == field) {
                throw new ErrorMessageException(tableName + "不存在唯一标示，无法进行查询");
            }
        } catch (ErrorMessageException e) {
            e.printStackTrace();
            return ;
        }
        DataTypes types = ObjectHelper.getFieldType(field);
        StringBuffer sql = SqlCreateUtils.createBaseUpdateSql(object);
        try{
            if (value instanceof String && (types.equals(DataTypes.TEXT)||types.equals(DataTypes.VARCHAR))) {
                sql.append(AND).append(keyName).append(EQUAL).append(SQUOTE).append(value).append(SQUOTE);
            } else if ((value instanceof Integer && types.equals(DataTypes.INTEGER)) || (value instanceof Long && types.equals(DataTypes.LONG)) || (value instanceof Float&&types.equals(DataTypes.FLOAT)) || (value instanceof Double)&&types.equals(DataTypes.DOUBLE)) {
                sql.append(AND).append(keyName).append(EQUAL).append(value);
            } else {
                throw new ErrorMessageException("->参数数据类型异常");
            }
        }catch (ErrorMessageException e){
            e.printStackTrace();
            return ;
        }
        LogUtils.e("updateByUniqueIden--->"+sql.toString());
        DBHelper.getInstance().execSQLwithTransaction(sql.toString());


    }

    @Override
    public void deleteByUniqueIden(Object value) {
        Field field = ObjectHelper.getPrimaryKeyField(clazz);
        String tableName = ObjectHelper.getTableName(clazz);
        String keyName = field.getName();
        try {
            if (null == field) {
                throw new ErrorMessageException(tableName + "不存在唯一标示，无法进行查询");
            }
        } catch (ErrorMessageException e) {
            e.printStackTrace();
            return ;
        }
        DataTypes types = ObjectHelper.getFieldType(field);
        StringBuffer sql = SqlCreateUtils.createBaseDeleteSql(clazz);
        try{
            if (value instanceof String && (types.equals(DataTypes.TEXT)||types.equals(DataTypes.VARCHAR))) {
                sql.append(AND).append(keyName).append(EQUAL).append(SQUOTE).append(value).append(SQUOTE);
            } else if ((value instanceof Integer && types.equals(DataTypes.INTEGER)) || (value instanceof Long && types.equals(DataTypes.LONG)) || (value instanceof Float&&types.equals(DataTypes.FLOAT)) || (value instanceof Double)&&types.equals(DataTypes.DOUBLE)) {
                sql.append(AND).append(keyName).append(EQUAL).append(value);
            } else {
                throw new ErrorMessageException("->参数数据类型异常");
            }
        }catch (ErrorMessageException e){
            e.printStackTrace();
            return ;
        }
        LogUtils.e("deleteByUniqueIden--->"+sql.toString());
        DBHelper.getInstance().execSQLwithTransaction(sql.toString());

    }
}
