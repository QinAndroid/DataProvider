package com.qzk.library.utils;

import com.qzk.library.entitys.QuerySort;
import com.qzk.library.enums.QuerySortType;
import com.qzk.library.helpers.ObjectHelper;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

/**
 * 类名：SqlCreateUtils
 * 描述：组装基本sql
 * 包名： com.qzk.library.utils
 * 项目名：DataProvider
 * Created by qinzongke on 6/29/16.
 */
public class SqlCreateUtils {
    /**
     * select * from xx where 1=1
     */
    private static final String SELECT = "select ";
    private static final String SELECTALL = " * ";
    private static final String FROM = " from ";
    private static final String WHERE = " where 1=1 ";

    /**
     * update xx set xx=xx ,xx=xx where 1=1
     */
    private static final String UPDATE = "update ";
    private static final String SET = " set ";
    /**
     * delete from xx where 1=1
     */
    private static final String DELETE = "delete from ";

    //common
    private static final String COMMA = ",";//,逗号
    private static final String ORDERBY = " order by ";
    private static final String ASCENDING = " ASC ";//升序
    private static final String DESCENDING = " DESC ";//降序
    private static final String SPACE = " ";
    protected static final String EQUAL = "=";
    protected static final String SQUOTE = "'";//'单引号
    private static final int DEFALUTVALUE = -1111;


    /**
     * 创建基本查询语句
     * select * form table where 1=1;
     * @param fieldsName
     * @return
     */
    public static StringBuffer createBaseSelectSql(Class clazz,String ...fieldsName){
        StringBuffer baseSelectSql = new StringBuffer();
        String tableName = ObjectHelper.getTableName(clazz);
        String selectFields = createSelectFields(fieldsName);
        baseSelectSql.append(SELECT).append(selectFields).append(FROM).append(tableName).append(WHERE);
        return baseSelectSql;
    }

    /**
     * 创建基本更新语句
     * update xx set xx=xx,xx=xx where 1=1
     * @param object
     * @return
     */
    public static StringBuffer createBaseUpdateSql(Object object){
        Class clazz = object.getClass();
        StringBuffer baseUpdateSql = new StringBuffer();
        String tableName = ObjectHelper.getTableName(clazz);
        baseUpdateSql.append(UPDATE).append(tableName).append(SET);
        String setSql = createSetFields(object);
        baseUpdateSql.append(setSql);
        return baseUpdateSql;
    }

    /**
     * 删除数据基本sql
     * delete from xx where 1=1
     * @param clazz
     * @return
     */
    public static StringBuffer createBaseDeleteSql(Class clazz){
        StringBuffer baseDeleteSql = new StringBuffer();
        String tableName = ObjectHelper.getTableName(clazz);
        baseDeleteSql.append(DELETE).append(tableName).append(WHERE);
        return baseDeleteSql;
    }

    /**
     * 构建更新字段
     * xx=xx,xx==xx
     * @param object
     * @return
     */
    private static String createSetFields(Object object) {
        StringBuffer sql = new StringBuffer();
        Class clazz = object.getClass();
        Field[] fields = ObjectHelper.getObjectFields(clazz);
        for (Field field : fields) {
            if (field.isSynthetic()) {
                continue;
            }
            if(ObjectHelper.fieldIsPrimaryKey(field)){
                continue;
            }
            field.setAccessible(true);
            Object value = ObjectHelper.getObjectValue(object, field);
            if(null == value){
                continue;
            }
            if ((value instanceof Integer) || (value instanceof Long) || (value instanceof Float) || (value instanceof Double)){
                if((Integer)value == DEFALUTVALUE){
                    continue;
                }
            }
            String fieldName = field.getName();
            sql.append(fieldName).append(EQUAL);
            if (value instanceof String) {
                sql.append(SQUOTE).append(value).append(SQUOTE);
            } else if ((value instanceof Integer) || (value instanceof Long) || (value instanceof Float) || (value instanceof Double)) {
                sql.append(value);
            }
            sql.append(COMMA);
        }
        if(sql.toString().endsWith(COMMA)){
            sql = new StringBuffer(sql.substring(0,sql.length()-1));
        }
        sql.append(WHERE);
        return sql.toString();
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
            sb.append(field).append(COMMA);
        }
        String selectFields = SELECTALL;
        if(sb.length()>1){
            selectFields = sb.subSequence(0, sb.length() - 1).toString();
        }
        return selectFields;
    }

    /**
     * 添加排序
     * @param sorts
     */
    public static String  createSortSql(List<QuerySort> sorts){
        if(null == sorts && sorts.size() == 0){
            return "";
        }
        StringBuffer sql = new StringBuffer();
        sql.append(ORDERBY);
        for (QuerySort sort : sorts) {
            if (null == sort) {
                continue;
            }
            String orderFieldName = sort.getFieldsName();
            String orderType = getSortType(sort.getType());
            sql.append(orderFieldName).append(SPACE).append(orderType).append(COMMA);
        }
        if (sql.toString().endsWith(COMMA)) {
            sql = new StringBuffer(sql.subSequence(0, sql.length() - 1).toString());
        }
        return sql.toString();

    }

    /**
     * 单个排序
     * @param sort 排序字段及排序方式
     *
     */
    public static String createSignSort(QuerySort sort){
        if(null == sort){
            return "";
        }
        String orderFieldName = sort.getFieldsName();
        String orderType = getSortType(sort.getType());
        StringBuffer sql = new StringBuffer();
        sql.append(ORDERBY).append(orderFieldName).append(orderType);
        return sql.toString();
    }


    /**
     * 获取排序类型
     * @param type
     * @return
     */
    private static String getSortType(QuerySortType type) {
        if (QuerySortType.ASCENDING == type) {
            return ASCENDING;
        } else if (QuerySortType.DESCENDING == type) {
            return DESCENDING;
        }
        return ASCENDING;
    }
}
