package com.qzk.library.entitys;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名：MultiQuerySorts
 * 描述：
 * 包名： com.qzk.library.entitys
 * 项目名：DataProvider
 * Created by qinzongke on 6/30/16.
 */
public class MultiQuerySorts {

    private List<QuerySort> sortAll = new ArrayList<>();



    public void add(QuerySort ...sorts){
        for(QuerySort sort : sorts){
            if(null == sort){
                continue;
            }
            sortAll.add(sort);
        }
    }

    public List<QuerySort> getSortAll(){
        return this.sortAll;
    }
}
