package com.qzk.library;

import android.content.ContentValues;
import android.util.Log;

import com.qzk.library.annotations.PrimaryKey;
import com.qzk.library.annotations.enums.DataTypes;
import com.qzk.library.annotations.enums.PrimaryKeyTypes;
import com.qzk.library.condition.Conditions;
import com.qzk.library.condition.CreateConditions;
import com.qzk.library.utils.LogUtils;

import java.lang.reflect.Field;

/**
 * Created by qinzongke on 16/6/21.
 */
public class CreateSQLHelper {

    private static final String CREATETABLE = "create table if not exists ";
    private static final String PRIMARYKEY = "  primary key ";
    private static final String AUTOINCREMENT = "autoincrement";
    private static final String SELECT = "select ";
    private static final String SELECTALL = " * ";
    private static final String FROM = " from ";
    private static final String WHERE = " where 1=1 ";
    private static final String LIMIT = " limit ";


    /**
     * 获取数据表名称
     *
     * @param clazz
     * @return
     */
    public static String getTableName(Class clazz) {
        String tableName = clazz.getSimpleName().toLowerCase();
        LogUtils.e("TableName---->" + tableName);
        return tableName;
    }

    /**
     * 生成创建表sql
     *
     * @param clazz
     * @return
     */
    public static String createTablesSql(Class clazz) {
        StringBuffer sb = new StringBuffer();
        String tableName = getTableName(clazz);
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
     * 根据条件查询单条数据
     *
     * @param clazz
     * @param conditions
     * @return
     */
    public static String createQueryFirstSql(Class clazz, String conditions) {
        StringBuffer sb = new StringBuffer();
        String tableName = getTableName(clazz);
        sb.append(SELECT).append(SELECTALL).append(FROM).append(tableName).append(WHERE).append(conditions);
        sb.append(LIMIT).append("0,1");
        LogUtils.e("queryFirstSql" + sb.toString());
        return sb.toString();
    }

    /**
     * 生成查询数据表所有数据
     *
     * @param clazz
     * @return
     */
    public static String createFindAllSql(Class clazz, String... fields) {
        StringBuffer sb = new StringBuffer();
        String tableName = getTableName(clazz);
        sb.append(SELECT);
        sb.append(createSelectFields(fields));
        sb.append(FROM).append(tableName);
        LogUtils.e("findAllSql---->" + sb.toString());
        return sb.toString();
    }


    /**
     * 组合查询字段
     * @param fields
     * @return
     */
    private static String createSelectFields(String... fields) {
        StringBuffer sb = new StringBuffer();
        for (String field : fields) {
            if(field == null || field.equals("")){
                continue;
            }
            sb.append(field).append(",");
        }
        String selectFields = SELECTALL;
        LogUtils.e("SSS----->"+sb.toString());
        if(sb.length()>1){
            selectFields = sb.subSequence(0, sb.length() - 1).toString();
        }
        return selectFields;
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
