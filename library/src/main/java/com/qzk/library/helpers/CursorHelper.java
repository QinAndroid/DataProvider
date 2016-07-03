package com.qzk.library.helpers;

import android.database.Cursor;

import com.qzk.library.annotations.enums.DataTypes;
import com.qzk.library.utils.LogUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 类名：CursorHelper
 * 描述：将执行查询sql的结果转换为对应的Object
 * 包名： com.qzk.library.helpers
 * 项目名：DataProvider
 * Created by qinzongke on 6/23/16.
 */
public class CursorHelper {

    /**
     * 将查询出的cursor转换为object
     *
     * @param cursor
     * @param clazz
     * @return
     */
    public static Object cursorToObject(Cursor cursor, Class clazz) {
        if (null == cursor) {
            LogUtils.e("cursor is null");
            return null;
        }
        Object result = ObjectHelper.classNewInstance(clazz);
        Field[] fields = ObjectHelper.getObjectFields(clazz);
        int len = fields.length;
        for (int i = 0; i < len; i++) {
            Field field = fields[i];
            if (field.isSynthetic()) {
                continue;
            }
            field.setAccessible(true);
            String fieldName = field.getName();
            DataTypes type = ObjectHelper.getFieldType(field);
            int columnIndex = cursor.getColumnIndex(fieldName);
            if (-1 == columnIndex) {
                continue;
            }
            Object setValue = null;
            if (DataTypes.INTEGER.equals(type)) {
                setValue = cursor.getInt(columnIndex);
            } else if (DataTypes.VARCHAR.equals(type) || DataTypes.TEXT.equals(type)) {
                setValue = cursor.getString(columnIndex);
            } else if (DataTypes.DOUBLE.equals(type)) {
                setValue = cursor.getDouble(columnIndex);
            } else if (DataTypes.FLOAT.equals(type)) {
                setValue = cursor.getFloat(columnIndex);
            } else if (DataTypes.LONG.equals(type)) {
                setValue = cursor.getLong(columnIndex);
            }
            if (null != setValue) {
                ObjectHelper.setObjectValue(field, result, setValue);
            }
        }
        return result;
    }

    /**
     * 查询结果多条
     *
     * @param cursor
     * @param clazz
     * @return
     */
    public static List<Object> cursorToList(Cursor cursor, Class clazz) {
        List<Object> result = null;
        if (null != cursor) {
            result = new ArrayList<>();
            while (cursor.moveToNext()) {
                Object object = ObjectHelper.classNewInstance(clazz);
                Field[] fields = ObjectHelper.getObjectFields(clazz);
                int len = fields.length;
                for (int i = 0; i < len; i++) {
                    Field field = fields[i];
                    if (field.isSynthetic()) {
                        continue;
                    }
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    DataTypes type = ObjectHelper.getFieldType(field);
                    int columnIndex = cursor.getColumnIndex(fieldName);
                    if (-1 == columnIndex) {
                        continue;
                    }
                    Object setValue = null;
                    if (DataTypes.INTEGER.equals(type)) {
                        setValue = cursor.getInt(columnIndex);
                    } else if (DataTypes.VARCHAR.equals(type) || DataTypes.TEXT.equals(type)) {
                        setValue = cursor.getString(columnIndex);
                    } else if (DataTypes.DOUBLE.equals(type)) {
                        setValue = cursor.getDouble(columnIndex);
                    } else if (DataTypes.FLOAT.equals(type)) {
                        setValue = cursor.getFloat(columnIndex);
                    } else if (DataTypes.LONG.equals(type)) {
                        setValue = cursor.getLong(columnIndex);
                    }
                    if (null != setValue) {
                        ObjectHelper.setObjectValue(field, object, setValue);
                    }
                    result.add(object);
                }
            }
        }
        return result;
    }
}
