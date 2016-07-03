package com.qzk.library.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.qzk.library.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinzongke on 16/6/21.
 */
public class DBHelper {

    private static DBHelper instance;
    private SQLiteDatabase db;

    private DBHelper() {
    }

    public static DBHelper getInstance() {
        if (null == instance) {
            instance = new DBHelper();
        }
        return instance;
    }

    /**
     * 创建数据库
     *
     * @param context
     * @param dataBaseName
     */
    public void createDataBase(Context context, String dataBaseName) {
        this.db = BaseSqliteHelper.getInstance(context, dataBaseName).getDataBase();
    }


    /**
     * 查询clazz全部数据
     * @param clazz
     * @return
     */
    public List<Object> findAll(Class clazz,  String sql) {
        Cursor cursor = query(sql);
        List<Object> result = null;
        if (null != cursor) {
            result = new ArrayList<>();
            while (cursor.moveToNext()){
                Object res = CursorHelper.cursorToObject(cursor,clazz);
                if(null != res){
                    result.add(res);
                }
            }
            cursor.close();
        }
        return result;

    }



    /**
     * 根据条件查询第一条数据
     * @param clazz
     * @param sql
     * @return
     */
    public Object findFist(Class clazz,String sql){
        Cursor cursor = query(sql);
        Object object = null;
        while (cursor.moveToNext()){
             object = CursorHelper.cursorToObject(cursor,clazz);
        }
        return object;
    }



    /**
     * 查询
     * @param sql
     * @return
     */
    private Cursor query(String sql) {
        return db.rawQuery(sql, null);
    }


    /**
     * 执行带事务sql
     * @param sql
     */
    public void execSQLwithTransaction(String sql){
        this.db.beginTransaction();
        try {
            this.db.execSQL(sql);
            this.db.setTransactionSuccessful();
        }finally {
            this.db.endTransaction();
        }
    }

    /**
     * 执行不带事务sql
     * @param sql
     */
    public void execSQL(String sql){
        this.db.execSQL(sql);
    }


}
