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
     * 根据字段及字段对应的值进行查询
     * @param value 字段名称 字段的值
     * @param selectFields 所需查询字段
     * @return
     */
    List<Object> findByField(KeyValue value,String...selectFields);

    /**
     * 根据字段及字段对应的值进行查询
     * @param value 字段名称 字段的值
     * @param sort 排序
     * @param selectFields 所需查询字段
     * @return
     */
    List<Object> findByField(KeyValue value,QuerySort sort,String...selectFields);

    /**
     * 根据字段及字段对应的值进行查询
     * @param value 字段名称 字段的值
     * @param sorts 排序
     * @param selectFields 所需查询字段
     * @return
     */
    List<Object> findByField(KeyValue value,MultiQuerySorts sorts,String ...selectFields);


    /**
     * 根据多字段及对应的值进行 查询
     * @param fields 多个键值对
     * @param selectFields 所需查询字段
     * @return
     */
    List<Object> findByFields(MultiQueryFields fields, String...selectFields);

    /**
     * 根据多字段及对应的值进行 单排序查询
     * @param fields 多个键值对
     * @param sort 排序字段
     * @param selectFields 所需查询字段
     * @return
     */
    List<Object> findByFields(MultiQueryFields fields, QuerySort sort, String...selectFields);

    /**根据多字段及对应的值进行多字段排序 查询
     * @param fields 查询条件的键值对
     * @param sorts 排序条件
     * @param selectFields 所需查询字段
     * @return
     */
    List<Object> findByFields(MultiQueryFields fields, MultiQuerySorts sorts, String...selectFields);
}
