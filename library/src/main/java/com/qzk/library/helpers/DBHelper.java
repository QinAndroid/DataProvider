package com.qzk.library.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.qzk.library.annotations.enums.DataTypes;
import com.qzk.library.query.Query;
import com.qzk.library.utils.LogUtils;

import java.lang.reflect.Field;
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
     * 创建单张数据表
     *
     * @param clazz
     */
    public void createTable(Class clazz) {
        String table = CreateSQLHelper.createTablesSql(clazz);
        if (null != this.db) {
            this.createTable(table);
            LogUtils.e("create table success");
        } else {
            LogUtils.e("please create DataBase first");
        }
    }

    /**
     * 创建多张数据表
     *
     * @param tables
     */
    public void createTables(List<Class> tables) {
        for (Class clazz : tables) {
            createTable(clazz);
        }
    }

    /**
     * 插入数据
     *
     * @param object
     * @return
     */
    public long insertToTable(Object object) {
        LogUtils.e("insert------->");
        ContentValues values = CreateSQLHelper.createInsertSql(object);
        long resutl = this.insert(ObjectHelper.getTableName(object.getClass()), values);
        LogUtils.e("insertSuccess--->" + resutl);
        return resutl;
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
     * 更新数据
     * @param sql
     */
    public void updateData(String sql){
        this.update(sql);
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
     * 插入数据
     *
     * @param table
     * @param values
     * @return
     */
    private long insert(String table, ContentValues values) {
        long result = this.db.insert(table, null, values);
        return result;
    }

    private void update(String sql){
        this.db.beginTransaction();
        try {
            String s = "update test set name = 'name1' where id = 1";
            this.db.execSQL(s);
            this.db.setTransactionSuccessful();
            LogUtils.e("update--->");
        }finally {
            this.db.endTransaction();
        }

    }

    /**
     * 创建数据表
     *
     * @param sql
     */
    private void createTable(String sql) {
        this.db.execSQL(sql);
    }
}
