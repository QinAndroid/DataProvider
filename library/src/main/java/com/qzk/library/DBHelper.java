package com.qzk.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.qzk.library.utils.LogUtils;

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
     * @param object
     */
    public void createTable(Object object) {
        String table = CreateSQLHelper.createTables(object);
        if (null != this.db) {
            this.execNoTranscationSql(table);
            LogUtils.e("create table success");
        } else {
            LogUtils.e("please create DataBase first");
        }
    }

    /**
     * 创建多张数据表
     * @param tables
     */
    public void createTables(List<Object> tables) {
        for (Object object : tables) {
            createTable(object);
        }
    }

    public long insertToTable(Object object){
        LogUtils.e("insert------->");
        ContentValues values = CreateSQLHelper.createInsertSql(object);
        long resutl = this.insert(CreateSQLHelper.getTableName(object),values);
        LogUtils.e("insertSuccess--->"+resutl);
        return 1;
    }

    private long insert(String table, ContentValues values){
        long result = this.db.insert(table,null,values);
        return result;
    }

    /**
     * 执行无事务sql
     *
     * @param sql
     */
    private void execNoTranscationSql(String sql) {
        this.db.execSQL(sql);
    }
}
