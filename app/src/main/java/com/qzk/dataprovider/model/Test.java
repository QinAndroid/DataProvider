package com.qzk.dataprovider.model;

import com.qzk.library.annotations.DataType;
import com.qzk.library.annotations.enums.DataTypes;
import com.qzk.library.annotations.PrimaryKey;
import com.qzk.library.annotations.enums.PrimaryKeyTypes;

/**
 * Created by qinzongke on 16/6/21.
 */
public class Test {

    @DataType(DataTypes.INTEGER)
    @PrimaryKey(PrimaryKeyTypes.AUTOINCREMENT)
    public int id;
    @DataType(DataTypes.VARCHAR)
    public String name;
    @DataType(DataTypes.INTEGER)
    public int age;
    @DataType(DataTypes.VARCHAR)
    public String address;

}
