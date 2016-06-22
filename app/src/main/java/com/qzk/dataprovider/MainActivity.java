package com.qzk.dataprovider;

import android.app.Activity;
import android.os.Bundle;

import com.qzk.dataprovider.model.Test;
import com.qzk.library.DBHelper;
import com.qzk.library.condition.Conditions;
import com.qzk.library.condition.CreateConditions;
import com.qzk.library.utils.LogUtils;

import java.util.List;

public class MainActivity extends Activity {

    private Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createTable();
        insertData();
        queryAll();
//        queryFirst();
    }

    private void createTable(){
        DBHelper.getInstance().createTable(Test.class);
    }

    private void insertData(){
        Test test = new Test();
        test.address = "address";
        test.name = "name";
        test.age = 15;
        DBHelper.getInstance().insertToTable(test);
    }
    private void queryAll(){
        List<Object> results = DBHelper.getInstance().findAll(Test.class,"name");
        Test test = null;
        for (Object object : results){
            test = (Test)object;
            LogUtils.e("id====>"+test.id);
            LogUtils.e("name===>"+test.name);
            LogUtils.e("address===>"+test.address);
            LogUtils.e("age===>"+test.age);
        }
        LogUtils.e("ResultSize----->"+results.size());
    }

    private void queryFirst(){
        CreateConditions conditions = new CreateConditions();
        String query = conditions.setConditions(new Conditions("name","name")).create();
        Test test = (Test) DBHelper.getInstance().findFist(Test.class,query);
        LogUtils.e("id====>"+test.id);
        LogUtils.e("name===>"+test.name);
        LogUtils.e("address===>"+test.address);
        LogUtils.e("age===>"+test.age);
    }
}
