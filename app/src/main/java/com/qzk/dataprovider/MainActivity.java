package com.qzk.dataprovider;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.qzk.dataprovider.model.Test;
import com.qzk.library.DBHelper;
import com.qzk.library.utils.LogUtils;

public class MainActivity extends Activity {

    private Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper.getInstance().createTable(new Test());
        Test test = new Test();
        test.address = "---->address=====>";
        test.id = 1;
        test.age = 2;
        test.name = "---->name====>";
//
        long result = DBHelper.getInstance().insertToTable(test);

    }
}
