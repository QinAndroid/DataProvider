package com.qzk.library.querybuilder;

import com.qzk.library.Exception.ErrorMessageException;
import com.qzk.library.entitys.MultiQuerySorts;
import com.qzk.library.entitys.QuerySort;
import com.qzk.library.enums.QuerySortType;
import com.qzk.library.helpers.DBHelper;
import com.qzk.library.utils.LogUtils;
import com.qzk.library.utils.SqlCreateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名：QueryBuilder
 * 描述：
 * 包名： com.qzk.library.querybuilder
 * 项目名：DataProvider
 * Created by qinzongke on 6/27/16.
 */
public class QueryBuilder implements IQuery{
    private StringBuffer where = new StringBuffer();
    private Class clazz;

    private int limitStart;
    private int limitEnd;


    protected static final String AND = " and ";
    protected static final String OR = " or ";
    protected static final String EQUAL = "=";
    protected static final String NOTEQUAL = "!=";
    protected static final String LIKE = " like ";
    protected static final String LIKESYSBOM = "%";
    protected static final String SQUOTE = "'";//'单引号

    private static final String COMMA = ",";//,逗号
    private static final String ORDERBY = " order by ";
    private static final String ASCENDING = " ASC ";//升序
    private static final String DESCENDING = " DESC ";//降序
    private static final String LIMIT = " limit ";
    private static final String SPACE = " ";
    protected boolean isOr = false;


    public QueryBuilder(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * 查询 =
     *
     * @param fieldName
     * @param value
     * @return
     */
    public QueryBuilder equal(String fieldName, Object value) {
        try {
            appendAndOR();
            where.append(fieldName).append(EQUAL);
            if (value instanceof String) {
                where.append(SQUOTE).append(value).append(SQUOTE);
            } else if ((value instanceof Integer) || (value instanceof Long) || (value instanceof Float) || (value instanceof Double)) {
                where.append(value);
            } else {
                throw new ErrorMessageException("Query->equal->参数数据类型异常");
            }
            resetIsOr();
        } catch (ErrorMessageException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * notEqual !=
     *
     * @param fieldName
     * @param value
     * @return
     */
    public QueryBuilder notEqual(String fieldName, String value) {
        appendAndOR();
        where.append(fieldName).append(NOTEQUAL).append(SQUOTE).append(value).append(SQUOTE);
        resetIsOr();
        return this;
    }

    /**
     * like like '%str%'
     *
     * @param fieldName
     * @param value
     * @return
     */
    public QueryBuilder like(String fieldName, String value) {
        appendAndOR();
        where.append(fieldName).append(LIKE).append(SQUOTE).append(LIKESYSBOM).append(value).append(LIKESYSBOM).append(SQUOTE);
        resetIsOr();
        return this;
    }


    /**
     * 判断查询条件是and还是or
     */
    private void appendAndOR() {
        if (isOr) {
            where.append(OR);
        } else {
            where.append(AND);
        }
    }

    /**
     * 每次添加条件后 将or改变为and
     * 再次添加条件默认为and
     */
    protected void resetIsOr() {
        if (isOr) {
            isOr = false;
        }
    }

    /**
     * 默认查询条件为and 修改条件为or
     *
     * @return
     */
    public QueryBuilder or() {
        isOr = true;
        return this;
    }


    /**
     * limit
     *
     * @param start
     * @param end
     */
    public QueryBuilder limit(int start, int end) {
        limitStart = start;
        limitEnd = end;
        return this;
    }


    /**
     * 按条件查询全部数据
     *
     * @param fieldsName 所需查询的字段名称
     * @return
     */
    @Override
    public List<Object> findAll(String... fieldsName) {
        StringBuffer sql = SqlCreateUtils.createBaseSelectSql(clazz, fieldsName);
        if (limitEnd != 0) {
            where.append(LIMIT).append(limitStart).append(COMMA).append(limitEnd);
        }
        sql.append(where);
        LogUtils.e("findAll--->" + sql.toString());
        return DBHelper.getInstance().findAll(clazz,sql.toString());
    }

    /**
     * 根据条件查询并根据多个条件进行结果排序
     *
     * @param sorts     排序条件集合
     * @param fieldName 查询字段名称
     * @return
     */
    @Override
    public List<Object> findAll(MultiQuerySorts sorts, String... fieldName) {
        String sortSql = SqlCreateUtils.createSortSql(sorts.getSortAll());
        where.append(sortSql);
        if (limitEnd != 0) {
            where.append(LIMIT).append(limitStart).append(COMMA).append(limitEnd);
        }
        StringBuffer sql = SqlCreateUtils.createBaseSelectSql(clazz, fieldName);
        sql.append(where);
        LogUtils.e("findAllSorts--->" + sql.toString());
        return DBHelper.getInstance().findAll(clazz,sql.toString());

//        return DBHelper.getInstance().findAll(this.clazz, sql.toString());
    }

    /**
     * 根据条件查询和单个排序条件进行结果排序
     *
     * @param sort
     * @param fieldName
     * @return
     */
    @Override
    public List<Object> findAll(QuerySort sort, String... fieldName) {
        String sortSql = SqlCreateUtils.createSignSort(sort);
        where.append(sortSql);
        if (limitEnd != 0) {
            where.append(LIMIT).append(limitStart).append(COMMA).append(limitEnd);
        }
        StringBuffer sql = SqlCreateUtils.createBaseSelectSql(clazz, fieldName);
        sql.append(where);
        LogUtils.e("findAllSort--->" + sql.toString());
        return DBHelper.getInstance().findAll(clazz,sql.toString());

    }

    /**查询单条数据
     * @return
     */
    @Override
    public Object findFirst(String... fieldsName) {
        StringBuffer sql = SqlCreateUtils.createBaseSelectSql(clazz, fieldsName);
        where.append(LIMIT).append(0).append(COMMA).append(1);
        sql.append(where);
        LogUtils.e("findFirst--->" + sql.toString());
        return DBHelper.getInstance().findFist(clazz,sql.toString());
    }

    /**
     * 更新数据
     * @param object
     */
    @Override
    public void update(Object object) {
        StringBuffer sql = SqlCreateUtils.createBaseUpdateSql(object);
        sql.append(where);
        LogUtils.e("update--->"+sql.toString());
        DBHelper.getInstance().execSQLwithTransaction(sql.toString());
    }


    /**
     * 删除数据
     */
    @Override
    public void delete() {
        StringBuffer sql = SqlCreateUtils.createBaseDeleteSql(clazz);
        sql.append(where);
        LogUtils.e("delete--->"+sql.toString());
        DBHelper.getInstance().execSQLwithTransaction(sql.toString());
    }


}
