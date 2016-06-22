package com.qzk.library.condition;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名：CreateConditions
 * 描述：
 * 包名： com.qzk.library.condition
 * 项目名：DataProvider
 * Created by qinzongke on 6/22/16.
 */
public class CreateConditions {


    private ArrayList<Conditions> conditionsList = new ArrayList<>();

    public CreateConditions setConditions(Conditions conditions) {
        conditionsList.add(conditions);
        return this;
    }

    /**
     * 获取查询条件
     * @return
     */
    public String create() {
        if (conditionsList.size() == 0) {
            return " ";
        }
        StringBuffer sb = new StringBuffer();
        for (Conditions conditions : conditionsList) {
            Object object = conditions.getValue();
            if(null == object){
                continue;
            }
            sb.append(" and ");
            if(object instanceof String){
                sb.append(conditions.getKey()).append(" = '").append(object.toString()).append("'");
            }else{
                sb.append(conditions.getKey()).append(" = ").append(object);
            }

        }
        return sb.toString();
    }


}
