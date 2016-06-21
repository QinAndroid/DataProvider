package com.qzk.dataprovider.model;

import com.qzk.library.annotations.DataType;
import com.qzk.library.annotations.DataTypes;
import com.qzk.library.annotations.PrimaryKey;

/**
 * Created by qinzongke on 16/6/21.
 */
public class Test {

    @DataType(DataTypes.INT)
    @PrimaryKey
    public int id;
    @DataType(DataTypes.VARCHAR)
    public String name;
    @DataType(DataTypes.INT)
    public int age;
    @DataType(DataTypes.VARCHAR)
    public String address;

}
