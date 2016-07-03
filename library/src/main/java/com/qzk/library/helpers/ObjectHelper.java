package com.qzk.library.helpers;

import com.qzk.library.annotations.DataType;
import com.qzk.library.annotations.enums.DataTypes;
import com.qzk.library.annotations.PrimaryKey;
import com.qzk.library.annotations.enums.PrimaryKeyTypes;
import com.qzk.library.utils.LogUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by qinzongke on 6/22/16.
 */
public class ObjectHelper {

    /**
     * 获取数据表名称
     *
     * @param clazz
     * @return
     */
    public static String getTableName(Class clazz) {
        String tableName = clazz.getSimpleName().toLowerCase();
        return tableName;
    }

    /**
     * 获取object的所有字段
     * @param clazz
     * @return
     */
    public static Field[] getObjectFields(Class clazz){
        return clazz.getDeclaredFields();
    }

    /**
     * 判断该字段是否为主键
     * @param field
     * @return
     */
    public static boolean fieldIsPrimaryKey(Field field){
       return field.isAnnotationPresent(PrimaryKey.class);
    }

    /**
     * 获取主键
     * @param clazz
     * @return
     */
    public static Field getPrimaryKeyField(Class clazz){
        Field[]fields = getObjectFields(clazz);
        for(Field field : fields){
            if(fieldIsPrimaryKey(field)){
                return field;
            }
        }
        return null;
    }

    /**
     * 判断主键是否自增
     * @param field
     * @return
     */
    public static boolean fieldsIsAutoIncrement(Field field){
        if(fieldIsPrimaryKey(field)){
            if(field.getAnnotation(PrimaryKey.class).value().equals(PrimaryKeyTypes.AUTOINCREMENT)){
                return true;
            }
        }
        return false;
    }

    /**
     * 获取字段的注解类型
     * @param field
     * @return
     */
    public static DataTypes getFieldType(Field field){
        if(null != field.getAnnotation(DataType.class)){
            return field.getAnnotation(DataType.class).value();
        }else{
            return DataTypes.VARCHAR;
        }

    }

    /**
     * 根据字段获取值
     * @param object
     * @param field
     * @return
     */
    public static Object getObjectValue(Object object, Field field) {
        try {
            if (null == field || null == object) {
                return null;
            }
            field.setAccessible(true);
            return field.get(object);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置字段值
     * @param field 修改字段
     * @param object 所属对象
     * @param object1 修改值
     */
    public static void setObjectValue(Field field,Object object,Object object1){
        try{
            if(null == field){
                return;
            }
            field.set(object,object1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * clazz 生成实例
     * @param clazz
     * @return
     */
    public static Object classNewInstance(Class clazz) {
        try {
            Object object = clazz.newInstance();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断主键是否存在
     * @param clazz
     * @return
     */
    public static boolean isExistPrimaryKey(Class clazz){
        Field field = getPrimaryKeyField(clazz);
        if(null == field){
            return false;
        }else{
            return true;
        }
    }
}
