package com.qzk.library.annotations;

/**
 * Created by qinzongke on 16/6/21.
 */
public @interface DataType {

    public DataTypes value() default DataTypes.INT;
}
