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
        test();
//        createTable();
//        insertData();
//        queryFirst();
//        update();
//        query();
//        update();
//        query();
//        delete();
//        query();
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

    //DataProvider.getInstance().query(Test.class,fieldsName).equal(xx,xx).notequal(xx,xx).like(xx,xx).sort(xx,xx).limit(xx,xx).findAll();
    // DataProvider.getInstance().query(Test.class,fieldsName).equal(xx,xx).notequal(xx,xx).like(xx,xx).sort(xx,xx).limit(xx,xx).findFirst();
    //DataProvider.getInstance().update(test).equal(xx,xx).notequal(xx,xx).like(xx,xx).update();
    //DataProvider.getInstance().delete(Test.class).equal(xx,xx).notequal(xx,xx),like(xx,xx).delete();
    private void test() {
        //创建数据表
        db.createTable(Test.class);
        //插入数据
        Test test = new Test();
        test.setAddress("beijing");
        test.setName("qzk");
        test.setAge(15);
        db.insert(test);
        //条件查询
        db.where(Test.class).findAll();
        db.where(Test.class).equal("name","name").findAll();
        db.where(Test.class).equal("name","name").like("name","n").findAll("name","age");
        db.where(Test.class).equal("name","name").like("name","n").limit(0,10).findAll("name","age");
        db.where(Test.class).equal("name","name").limit(10,20).findAll(new QuerySort("name",QuerySortType.ASCENDING));
        db.where(Test.class).findAll(new QuerySort("age",QuerySortType.DESCENDING),"name","age");
        MultiQuerySorts sorts = new MultiQuerySorts();
        sorts.add(new QuerySort("age",QuerySortType.ASCENDING),new QuerySort("name",QuerySortType.DESCENDING));
        db.where(Test.class).findAll(sorts);
        db.where(Test.class).findAll(sorts,"name","test");
        //单条数据
        db.where(Test.class).equal("name","name").findFirst("name","age");
        //更新数据
        Test updateTest = new Test();
        updateTest.setAddress("Update");
        db.where(Test.class).equal("id",1).limit(1,10).update(updateTest);
        //删除数据
        db.where(Test.class).equal("id",1).delete();


        //条件查询并排序
//        db.where(Test.class).equal("name","name").or().like("name","n").findAllBySort(new QuerySort("name",QuerySortType.ASCENDING));

//        DataProvider.getInstance().query(Test.class).equal("name", "name").notEqual("name", "test").like("name", "n").or().like("name", "m").limit(0, 10).findAllBySort(new QuerySort("name", QuerySortType.ASCENDING), new QuerySort("type", QuerySortType.DESCENDING));
//        DataProvider.getInstance().query(Test.class).findAll();
    }

//    private void query() {
//        Query query = new Query();
//        query.from(Test.class);
////        query.sort(new QuerySort("id", QuerySortType.DESCENDING));
//        List<Object> results = DataProvider.getInstance().find(query);
//        Test test = null;
//        for (Object object : results) {
//            test = (Test) object;
//            LogUtils.e("id====>" + test.getId());
//            LogUtils.e("name===>" + test.getName());
//            LogUtils.e("address===>" + test.getAddress());
//            LogUtils.e("age===>" + test.getAge());
//        }
//        LogUtils.e("ResultSize----->" + results.size());
//    }
//
//    private void queryFirst() {
//        Query query = new Query();
//        query.from(Test.class);
////        query.sort(new QuerySort("id", QuerySortType.DESCENDING));
//        Test test = (Test) DataProvider.getInstance().findFirst(query);
//        LogUtils.e("id====>" + test.getId());
//        LogUtils.e("name===>" + test.getName());
//        LogUtils.e("address===>" + test.getAddress());
//        LogUtils.e("age===>" + test.getAge());
//    }
//
//    private void update() {
////        LogUtils.e("UPDATE--->");
////        Update update = new Update();
////        Test test = (Test) update.createUpdateInstance(Test.class);
////        test.setAddress("北京Update");
////        test.setAge(0);
////        test.setName("秦宗珂Update");
////        update.update(test).equal("id", 1);
////        DataProvider.getInstance().update(update);
//    }

    private void delete() {
//        Delete delete = new Delete();
//        delete.delete(Test.class).equal("id", 1);
//        DataProvider.getInstance().delete(delete);

    }
}
