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








    /**
     * 创建插入数据ContentValues
     *
     * @param object
     * @return
     */
//    public static ContentValues createInsertSql(Object object) {
//        ContentValues values = new ContentValues();
//        Field[] fields = ObjectHelper.getObjectFields(object.getClass());
//        if (null != fields) {
//            int len = fields.length;
//            Field field = null;
//            for (int i = 0; i < len; i++) {
//                field = fields[i];
//                if (field.isSynthetic()) {
//                    continue;
//                }
//                if (!ObjectHelper.fieldsIsAutoIncrement(field)) {
//                    setValuesToContent(object, field, values);
//                }
//
//            }
//        } else {
//            LogUtils.e("fields is null or length = 0; ");
//        }
//        return values;
//    }






    /**
     * 组合插入数据的ContentValues
     *
     * @param object 数据表
     * @param field  插入字段
     * @param values 引用传递
     */
//    private static void setValuesToContent(Object object, Field field, ContentValues values) {
//        try {
//            field.setAccessible(true);
//            DataTypes type = ObjectHelper.getFieldType(field);
//            String fieldName = field.getName();
//            Object value = ObjectHelper.getObjectValue(object, field);
//            if (null == value) {
//                LogUtils.e("get FieldValue is null");
//                return;
//            }
//            if (DataTypes.INTEGER.equals(type)) {
//                values.put(fieldName, (Integer) value);
//            } else if (DataTypes.VARCHAR.equals(type) || DataTypes.TEXT.equals(type)) {
//                values.put(fieldName, (String) value);
//            } else if (DataTypes.DOUBLE.equals(type)) {
//                values.put(fieldName, (Double) value);
//            } else if (DataTypes.FLOAT.equals(type)) {
//                values.put(fieldName, (Float) value);
//            } else if (DataTypes.LONG.equals(type)) {
//                values.put(fieldName, (Long) value);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }


}
