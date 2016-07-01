package com.qzk.library.operates;

import com.qzk.library.annotations.PrimaryKey;
import com.qzk.library.annotations.enums.PrimaryKeyTypes;
import com.qzk.library.helpers.CreateSQLHelper;
import com.qzk.library.helpers.ObjectHelper;
import com.qzk.library.utils.LogUtils;

import java.lang.reflect.Field;

/**
 * 类名：CreateTable
 * 描述：
 * 包名： com.qzk.library.operates
 * 项目名：DataProvider
 * Created by qinzongke on 6/25/16.
 */
public class CreateTable {
    private static final String CREATETABLE = "create table if not exists ";
    private static final String PRIMARYKEY = "  primary key ";
    private static final String AUTOINCREMENT = "autoincrement";
    private static final String COMMA = ",";
    private StringBuffer sql = new StringBuffer();


    /**
     * 创建数据表
     *
     * @param clazz
     * @return
     */
    public void createTable(Class clazz) {
        String tableName = ObjectHelper.getTableName(clazz);
        Field[] fields = ObjectHelper.getObjectFields(clazz);
        if (null != fields) {
            int len = fields.length;
            Field field = null;
            sql.append(CREATETABLE).append(tableName).append(" (");
            for (int i = 0; i < len; i++) {
                field = fields[i];
                if (field.isSynthetic()) {
                    continue;
                }
                field.setAccessible(true);
                sql.append(field.getName()).append(" ").append(ObjectHelper.getFieldType(field));
                if (ObjectHelper.fieldIsPrimaryKey(field)) {
                    sql.append(PRIMARYKEY);
                    if (field.getAnnotation(PrimaryKey.class).value().equals(PrimaryKeyTypes.AUTOINCREMENT)) {
                        sql.append(AUTOINCREMENT);
                    }
                }
                sql.append(COMMA);

            }
            if (sql.toString().endsWith(COMMA)) {
                sql = new StringBuffer(sql.subSequence(0, sql.length() - 1));
            }
            sql.append(")");
        } else {
            LogUtils.e("fields is null or length = 0; ");
        }
    }

    public String createSql() {
        return sql.toString();
    }


}
