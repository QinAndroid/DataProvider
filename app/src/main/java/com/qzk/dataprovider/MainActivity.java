package com.qzk.dataprovider;

import android.app.Activity;
import android.os.Bundle;

import com.qzk.dataprovider.model.Test;
import com.qzk.library.DataProvider;
import com.qzk.library.entitys.MultiQuerySorts;
import com.qzk.library.enums.QuerySortType;
import com.qzk.library.entitys.QuerySort;
import com.qzk.library.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private Activity activity;
    private DataProvider db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = DataProvider.getInstance();
        createTable();
        insertData();
//        query();
        queryIden();
    }

    private void createTable() {
        DataProvider.getInstance().createTable(Test.class);

    }

    private void insertData() {
        Test test = new Test();
        test.setAddress("beijing");
        test.setName("qzk");
        test.setAge(15);
        DataProvider.getInstance().insert(test);
    }

    private void query() {
        //创建数据表
        db.createTable(Test.class);
        //插入数据
        Test test = new Test();
        test.setAddress("beijing");
        test.setName("qzk");
        test.setAge(15);
        db.insert(test);
        //更新数据
        Test updateTest = new Test();
        updateTest.setAddress("Update");
        updateTest.setAge(1);
        db.where(Test.class).equal("id", 7).update(updateTest);
        //删除数据
//        db.where(Test.class).equal("id",1).delete();
        //条件查询
        List<Object> result = db.where(Test.class).findAll();
//        LogUtils.e("findAll--->");
//        printf(result);
//        result = db.where(Test.class).equal("name", "qzk").findAll();
//        LogUtils.e("findAll-->address=Update--->");
//        printf(result);
//        result = db.where(Test.class).equal("name", "qzk").like("address", "U").findAll("name", "address");
//        LogUtils.e("findAll-->name=qzk or address like U--->");
//        printf(result);
//        db.where(Test.class).equal("name","name").like("name","n").limit(0,10).findAll("name","age");
//       result= db.where(Test.class).equal("name","qzk").findAll(new QuerySort("id",QuerySortType.DESCENDING));
//        LogUtils.e("sort--->");
//        printf(result);
//        result = db.where(Test.class).findAll(new QuerySort("age", QuerySortType.DESCENDING), "name", "age");
//        LogUtils.e("sort--->");
//        printf(result);
//        MultiQuerySorts sorts = new MultiQuerySorts();
//        sorts.add(new QuerySort("age", QuerySortType.DESCENDING), new QuerySort("address", QuerySortType.DESCENDING));
//        result = db.where(Test.class).findAll(sorts);
//        LogUtils.e("multi--->");
//        printf(result);
//        db.where(Test.class).findAll(sorts,"name","test");
//        //单条数据
        Object object = db.where(Test.class).equal("age",1).findFirst("name","age");
        printf(object);



    }

    private void queryIden() {
        Test test = new Test();
        test.setAddress("UpdateIden--->");
        db.whereIden(Test.class).updateByUniqueIden(2, test);
        db.whereIden(Test.class).deleteByUniqueIden(2);
        Object object = db.whereIden(Test.class).findByUniqueIden(2);
        LogUtils.e("inden--->");
        printf(object);
    }


    private void printf(List<Object> result) {
        for (Object object : result) {
            Test r = (Test) object;
            LogUtils.e("Id--->" + r.getId());
            LogUtils.e("Name--->" + r.getName());
            LogUtils.e("Address--->" + r.getAddress());
            LogUtils.e("Age--->" + r.getAge());
        }
        LogUtils.e("ResultSize--->" + result.size());
    }

    private void printf(Object object){
        Test r = (Test) object;
        LogUtils.e("Id--->" + r.getId());
        LogUtils.e("Name--->" + r.getName());
        LogUtils.e("Address--->" + r.getAddress());
        LogUtils.e("Age--->" + r.getAge());
    }
}
