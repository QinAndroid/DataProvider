package com.qzk.library;

import android.content.ContentValues;

import com.qzk.library.annotations.DataType;
import com.qzk.library.annotations.DataTypes;
import com.qzk.library.annotations.PrimaryKey;
import com.qzk.library.utils.LogUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by qinzongke on 16/6/21.
 */
public class CreateSQLHelper {


    /**
     * 获取数据表名称
     *
     * @param object
     * @return
     */
    public static String getTableName(Object object) {
        String tableName = object.getClass().getSimpleName().toLowerCase().toString();
        return tableName;
    }

    /**
     * 生成创建表sql
     *
     * @param object
     * @return
     */
    public static String createTables(Object object) {
        StringBuffer sb = new StringBuffer();
        String tableName = getTableName(object);
        Field[] fields = object.getClass().getDeclaredFields();
        if (null != fields && fields.length > 1) {
            int len = fields.length - 1;
            Field field = null;
            sb.append("create table if not exists ").append(tableName).append(" (");
            for (int i = 0; i < len; i++) {
                field = fields[i];
                sb.append(field.getName()).append(" ").append(field.getAnnotation(DataType.class).value());
                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    sb.append("  primary key");
                }
                if (i != len - 1) {
                    sb.append(",");
                }

            }
            sb.subSequence(0, sb.length());
            sb.append(" )");
            LogUtils.e("sql--->" + sb.toString());
        } else {
            LogUtils.e("fields is null or length = 0; ");
        }
        return sb.toString();
    }

    public static ContentValues createInsertSql(Object object) {
        ContentValues values = new ContentValues();
        Field[] fields = object.getClass().getFields();
        if (null != fields && fields.length > 1) {
            int len = fields.length - 1;
            Field field = null;
            for (int i = 0; i < len; i++) {
                field = fields[i];
                setValuesToContent(object, field, values);
            }
        } else {
            LogUtils.e("fields is null or length = 0; ");
        }
        return values;
    }

    /**
     * 根据字段获取值
     *
     * @param object
     * @param field
     * @return
     */
    private static Object getObjectValue(Object object, Field field) {
        try {
            if (null == field || null == object) {
                return null;
            }
            return field.get(object);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void setValuesToContent(Object object, Field field, ContentValues values) {
        try {
            DataTypes type = field.getAnnotation(DataType.class).value();
            String fieldName = field.getName();
            Object value = field.get(object);
            if (DataTypes.INT.equals(type)) {
                values.put(fieldName, (Integer) value);
            } else if (DataTypes.VARCHAR.equals(type) || DataTypes.TEXT.equals(type)) {
                values.put(fieldName, (String) value);
            } else if (DataTypes.DOUBLE.equals(type)) {
                values.put(fieldName, (Double) object);
            } else if (DataTypes.FLOAT.equals(type)) {
                values.put(fieldName, (Float) object);
            } else if (DataTypes.LONG.equals(type)) {
                values.put(fieldName, (Long) object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
