package com.qzk.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.qzk.library.annotations.enums.DataTypes;
import com.qzk.library.condition.Conditions;
import com.qzk.library.condition.CreateConditions;
import com.qzk.library.utils.LogUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.BitSet;
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
            this.execNoTranscationSql(table);
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
        long resutl = this.insert(CreateSQLHelper.getTableName(object.getClass()), values);
        LogUtils.e("insertSuccess--->" + resutl);
        return resutl;
    }

    /**
     * 查询clazz全部数据
     * @param clazz
     * @return
     */
    public List<Object> findAll(Class clazz,  String... fields) {
        String sql = CreateSQLHelper.createFindAllSql(clazz,fields);
        Cursor cursor = query(sql);
        List<Object> result = null;
        if (null != cursor) {
            result = new ArrayList<>();
            while (cursor.moveToNext()){
                Object res = cursorToObject(cursor,clazz);
                if(null != res){
                    result.add(res);
                }
            }
        }
        return result;

    }

    private Object cursorToObject(Cursor cursor,Class clazz){
        if(null == cursor){
            LogUtils.e("cursor is null");
            return null;
        }
        Object result = ObjectHelper.classNewInstance(clazz);
        Field [] fields = ObjectHelper.getObjectFields(clazz);
        int len = fields.length;
        for (int i = 0;i<len;i++) {
            Field field = fields[i];
            if(field.isSynthetic()){
                continue;
            }
            String fieldName = field.getName();
            DataTypes type = ObjectHelper.getFieldType(field);
            int columnIndex = cursor.getColumnIndex(fieldName);
            if(-1 == columnIndex){
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
            if(null != setValue){
                ObjectHelper.setObjectValue(field,result,setValue);
            }
        }
        return result;
    }

    /**
     * 根据条件查询第一条数据
     * @param clazz
     * @param conditions
     * @return
     */
    public Object findFist(Class clazz, String conditions){
        String sql = CreateSQLHelper.createQueryFirstSql(clazz,conditions);
        Cursor cursor = query(sql);
        Object object = null;
        while (cursor.moveToNext()){
             object = cursorToObject(cursor,clazz);
        }
        return object;
    }

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

    /**
     * 执行无事务sql
     *
     * @param sql
     */
    private void execNoTranscationSql(String sql) {
        this.db.execSQL(sql);
    }
}
