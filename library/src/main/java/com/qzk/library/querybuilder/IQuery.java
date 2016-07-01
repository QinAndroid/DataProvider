package com.qzk.library.querybuilder;

import com.qzk.library.entitys.MultiQuerySorts;
import com.qzk.library.entitys.QuerySort;

import java.util.List;

/**
 * 类名：IQuery
 * 描述：
 * 包名： com.qzk.library.querybuilder
 * 项目名：DataProvider
 * Created by qinzongke on 6/29/16.
 */
public interface IQuery {

    /**
     * 按条件查询全部数据
     * @param fieldsName 所需查询的字段名称
     * @return
     */
    List<Object> findAll(String...fieldsName);

    /**
     * 根据条件查询并根据多个条件进行结果排序
     * @param sorts 排序条件集合
     * @param fieldName 查询字段名称
     * @return
     */
    List<Object> findAll(MultiQuerySorts sorts, String...fieldName);

    /**
     * 根据条件查询和单个拍序条件进行结果排序
     * @param sort
     * @param fieldName
     * @return
     */
    List<Object> findAll(QuerySort sort,String ...fieldName);
    /**
     * 按条件查询单条数据
     * @param fieldsName 所需查询的字段名称
     * @return
     */
    Object findFirst(String...fieldsName);

    /**
     * 更新数据
     * @param object
     */
    void update(Object object);

    /**
     * 删除数据
     */
    void delete();


}
