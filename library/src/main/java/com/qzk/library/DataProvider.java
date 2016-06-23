package com.qzk.library;

import com.qzk.library.Exception.ErrorMessageException;
import com.qzk.library.enums.QuerySortType;
import com.qzk.library.helpers.CreateSQLHelper;
import com.qzk.library.helpers.DBHelper;
import com.qzk.library.query.Query;
import com.qzk.library.utils.LogUtils;

import java.util.List;

/**
 * 类名：DataProvider
 * 描述：
 * 包名： com.qzk.library
 * 项目名：DataProvider
 * Created by qinzongke on 6/23/16.
 */
public class DataProvider {

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
     * 查询clazz表中数据
     *
     * @param query
     * @return
     */
    public List<Object> find(Query query) {
        try{
            if(null == query.getClass()){
                throw new ErrorMessageException("DataProvider->findFist->请先设置要查询的数据表");
            }
        }catch (ErrorMessageException e){
            e.printStackTrace();
        }
        String sortString = query.getSortString().toString();
        String limitString = query.getLimitString().toString();
        StringBuffer sql = query.createSqlQuery();
        if(null != sortString){
            sql.append(sortString);
        }
        if(null != limitString){
            sql.append(limitString);
        }
        LogUtils.e("querySql--->" + sql.toString());
        return DBHelper.getInstance().findAll(query.getClazz(), sql.toString());
    }


    /**
     * 查询单条数据
     * @param query
     * @return
     */
    public Object findFirst(Query query){
        try{
            if(null == query.getClass()){
                throw new ErrorMessageException("DataProvider->findFist->请先设置要查询的数据表");
            }
        }catch (ErrorMessageException e){
            e.printStackTrace();
        }
        String sortString = query.getSortString().toString();
        String limitString = " limit 0,1";
        StringBuffer sql = query.createSqlQuery();
        if(null != sortString){
            sql.append(sortString);
        }
        sql.append(limitString);
        LogUtils.e("queryFirstSql--->" + sql.toString());
        return DBHelper.getInstance().findFist(query.getClazz(), sql.toString());
    }

    public void update(Object object,Query query){

    }




}
