package com.qzk.dataprovider;

import android.app.Application;

import com.qzk.library.helpers.DBHelper;
import com.qzk.library.utils.LogUtils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by qinzongke on 16/6/21.
 */
public class BaseApplication extends Application {
    private final String databaseName = "test";
    @Override
    public void onCreate() {
        super.onCreate();
        DBHelper.getInstance().createDataBase(getApplicationContext(),databaseName);

    }


}
