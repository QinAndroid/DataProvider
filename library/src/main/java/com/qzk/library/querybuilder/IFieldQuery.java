package com.qzk.library.querybuilder;

import com.qzk.library.entitys.KeyValue;
import com.qzk.library.entitys.MultiQueryFields;
import com.qzk.library.entitys.MultiQuerySorts;
import com.qzk.library.entitys.QuerySort;

import java.util.List;

/**
 * 类名：IFieldQuery
 * 描述：
 * 包名： com.qzk.library.querybuilder
 * 项目名：DataProvider
 * Created by qinzongke on 6/30/16.
 */
public interface IFieldQuery {


    /**
     * 根据表中唯一标示查询
     * @param value 值
     * @param selectFields 所需查询出的字段名 默认为全部 *
     * @return
     */
    Object findByUniqueIden(Object value,String ...selectFields);


    /**
     * 根据唯一标记更细数据
     * @param value 值
     * @param object 所需更新实体
     */
    void updateByUniqueIden(Object value,Object object);

    /**
     * 根据唯一标示删除数据
     * @param value
     */
    void deleteByUniqueIden(Object value);



}
