package com.qzk.dataprovider;

import android.app.Activity;
import android.os.Bundle;

import com.qzk.dataprovider.model.Test;
import com.qzk.library.DataProvider;
import com.qzk.library.enums.QuerySortType;
import com.qzk.library.helpers.DBHelper;
import com.qzk.library.query.Query;
import com.qzk.library.query.QueryDo;
import com.qzk.library.query.QuerySort;
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
//        queryFirst();
//        update();
//        query();
//        update();
        query();
    }

    private void createTable() {
        DBHelper.getInstance().createTable(Test.class);
    }

    private void insertData() {
        Test test = new Test();
        test.setAddress("beijing");
        test.setName("qzk");
        test.setAge(15);
        DBHelper.getInstance().insertToTable(test);
    }

    private void query() {
        Query query = new Query();
        query.from(Test.class);
        query.sort(new QuerySort("id", QuerySortType.DESCENDING));
        List<Object> results = DataProvider.getInstance().find(query);
        Test test = null;
        for (Object object : results) {
            test = (Test) object;
            LogUtils.e("id====>" + test.getId());
            LogUtils.e("name===>" + test.getName());
            LogUtils.e("address===>" + test.getAddress());
            LogUtils.e("age===>" + test.getAge());
        }
        LogUtils.e("ResultSize----->" + results.size());
    }

    private void queryFirst() {
        Query query = new Query();
        query.from(Test.class);
        query.sort(new QuerySort("id", QuerySortType.DESCENDING));
        Test test = (Test) DataProvider.getInstance().findFirst(query);
        LogUtils.e("id====>" + test.getId());
        LogUtils.e("name===>" + test.getName());
        LogUtils.e("address===>" + test.getAddress());
        LogUtils.e("age===>" + test.getAge());
    }

    private void update() {
        LogUtils.e("UPDATE--->");
        DBHelper.getInstance().updateData("");
//        Test test =  new Test();
//        test.setAge(2);
//        QueryDo queryDo = new QueryDo();


    }
}
