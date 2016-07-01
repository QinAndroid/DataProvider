package com.qzk.dataprovider.model;

import com.qzk.library.annotations.DataType;
import com.qzk.library.annotations.enums.DataTypes;
import com.qzk.library.annotations.PrimaryKey;
import com.qzk.library.annotations.enums.PrimaryKeyTypes;
import com.qzk.library.entitys.Model;

/**
 * Created by qinzongke on 16/6/21.
 */
public class Test extends Model<Test>{

    @DataType(DataTypes.INTEGER)
    @PrimaryKey(PrimaryKeyTypes.AUTOINCREMENT)
    private int id;
    @DataType(DataTypes.VARCHAR)
    private String name;
    @DataType(DataTypes.INTEGER)
    private int age;
    @DataType(DataTypes.VARCHAR)
    private String address;

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
