package com.qzk.library;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by qinzongke on 16/6/21.
 */
public class BaseSqliteHelper extends SQLiteOpenHelper {

    private static BaseSqliteHelper instance;

    public static BaseSqliteHelper getInstance(Context context, String dataBaseName) {
        if (null == instance) {
            instance = new BaseSqliteHelper(context, dataBaseName);
        }

        return instance;
    }

    public SQLiteDatabase getDataBase(){
        return instance.getWritableDatabase();

    }



    private BaseSqliteHelper(Context context, String name) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
