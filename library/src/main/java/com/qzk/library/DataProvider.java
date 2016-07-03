package com.qzk.library;

import com.qzk.library.helpers.DBHelper;
import com.qzk.library.operates.CreateTable;
import com.qzk.library.operates.Insert;
import com.qzk.library.querybuilder.FieldQueryBuilder;
import com.qzk.library.querybuilder.IFieldQuery;
import com.qzk.library.querybuilder.QueryBuilder;
import com.qzk.library.utils.LogUtils;

/**
 * 类名：DataProvider
 * 描述：
 * 包名： com.qzk.library
 * 项目名：DataProvider
 * Created by qinzongke on 6/23/16.
 */
public class DataProvider {

    /**
     * 应用层调用
     * 建表:Class
     * 查询:Class   查询条件  排序  limit
     * 插入:Object
     * 更新:Object  查询条件
     * 删除:Class   查询条件
     */

    /**
     * 底层接口接收
     * 建表:sql create table xx if not exists
     * 查询 Class:用于反射出对象并设置对应字断的值,sql (query,sort,limit)select * from xxx where 1=1 and xx=xx order by xx limit x,x;
     * 插入 sql insert into xx(xx,xx,xx)values(xx,xx,xx);
     * 更新 sql update xx set xx=xx,xx=xx where 1=1 and xx=xx;
     * 删除 sql delete from xx where 1=1 and xx=xx;
     */

    private static DataProvider instance = null;

    private DataProvider() {
    }

    public static DataProvider getInstance() {
        if (null == instance) {
            synchronized (DataProvider.class) {
                if (null == instance) {
                    instance = new DataProvider();
                }
            }
        }
        return instance;
    }

    /**
     * 创建数据表
     * @param clazz
     */
    public void createTable(Class clazz){
        CreateTable create = new CreateTable();
        create.createTable(clazz);
        String sql = create.createSql();
        LogUtils.e("creteTableSql--->"+create.createSql());
        this.execSql(sql);
    }

    /**
     * 创建数据表
     * @param sql
     */
    public void createTable(String sql){
        LogUtils.e("createTableSql--->"+sql);
       this.execSql(sql);
    }


    /**
     * 插入数据
     * @param object
     */
    public void insert(Object object){
        Insert insert = new Insert();
        insert.insert(object);
        String sql = insert.createSql();
        LogUtils.e("insertSql--->"+sql);
        this.execSQLwithTran(sql);
    }


    /**
     * 根据指定条件 进行update delete  select
     * @param clazz 要操作的数据表
     * @return
     */
    public QueryBuilder where(Class clazz){
        return new QueryBuilder(clazz);
    }

    public IFieldQuery whereIden(Class clazz){
        return new FieldQueryBuilder(clazz);
    }


    /**
     * 执行带事务sql
     * @param sql
     */
    private void execSQLwithTran(String sql){
        DBHelper.getInstance().execSQLwithTransaction(sql);
    }

    /**
     * 执行不带事务sql
     * @param sql
     */
    private void execSql(String sql){
        DBHelper.getInstance().execSQL(sql);
    }





}
