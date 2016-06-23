package com.qzk.library.helpers;

import android.content.ContentValues;

import com.qzk.library.annotations.PrimaryKey;
import com.qzk.library.annotations.enums.DataTypes;
import com.qzk.library.annotations.enums.PrimaryKeyTypes;
import com.qzk.library.utils.LogUtils;

import java.lang.reflect.Field;

/**
 * Created by qinzongke on 16/6/21.
 */
public class CreateSQLHelper {

    private static final String CREATETABLE = "create table if not exists ";
    private static final String PRIMARYKEY = "  primary key ";
    private static final String AUTOINCREMENT = "autoincrement";




    /**
     * 生成创建表sql
     *
     * @param clazz
     * @return
     */
    public static String createTablesSql(Class clazz) {
        StringBuffer sb = new StringBuffer();
        String tableName = ObjectHelper.getTableName(clazz);
        Field[] fields = ObjectHelper.getObjectFields(clazz);
        if (null != fields) {
            int len = fields.length;
            Field field = null;
            sb.append(CREATETABLE).append(tableName).append(" (");
            for (int i = 0; i < len; i++) {
                field = fields[i];
                if (field.isSynthetic()) {
                    continue;
                }
                sb.append(field.getName()).append(" ").append(ObjectHelper.getFieldType(field));
                if (ObjectHelper.fieldIsPrimaryKey(field)) {
                    sb.append(PRIMARYKEY);
                    if (field.getAnnotation(PrimaryKey.class).value().equals(PrimaryKeyTypes.AUTOINCREMENT)) {
                        sb.append(AUTOINCREMENT);
                    }
                }
                sb.append(",");

            }
            int l = sb.length();
            sb = new StringBuffer(sb.subSequence(0, sb.length() - 1));
            sb.append(")");
            LogUtils.e("sql--->" + sb.toString());
        } else {
            LogUtils.e("fields is null or length = 0; ");
        }
        return sb.toString();
    }

    /**
     * 创建插入数据ContentValues
     *
     * @param object
     * @return
     */
    public static ContentValues createInsertSql(Object object) {
        ContentValues values = new ContentValues();
        Field[] fields = ObjectHelper.getObjectFields(object.getClass());
        if (null != fields) {
            int len = fields.length;
            Field field = null;
            for (int i = 0; i < len; i++) {
                field = fields[i];
                if (field.isSynthetic()) {
                    continue;
                }
                if (!ObjectHelper.fieldsIsAutoIncrement(field)) {
                    setValuesToContent(object, field, values);
                }

            }
        } else {
            LogUtils.e("fields is null or length = 0; ");
        }
        return values;
    }






    /**
     * 组合插入数据的ContentValues
     *
     * @param object 数据表
     * @param field  插入字段
     * @param values 引用传递
     */
    private static void setValuesToContent(Object object, Field field, ContentValues values) {
        try {
            field.setAccessible(true);
            DataTypes type = ObjectHelper.getFieldType(field);
            String fieldName = field.getName();
            Object value = ObjectHelper.getObjectValue(object, field);
            if (null == value) {
                LogUtils.e("get FieldValue is null");
                return;
            }
            if (DataTypes.INTEGER.equals(type)) {
                values.put(fieldName, (Integer) value);
            } else if (DataTypes.VARCHAR.equals(type) || DataTypes.TEXT.equals(type)) {
                values.put(fieldName, (String) value);
            } else if (DataTypes.DOUBLE.equals(type)) {
                values.put(fieldName, (Double) value);
            } else if (DataTypes.FLOAT.equals(type)) {
                values.put(fieldName, (Float) value);
            } else if (DataTypes.LONG.equals(type)) {
                values.put(fieldName, (Long) value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
