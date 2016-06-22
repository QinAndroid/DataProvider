package com.qzk.library.annotations;

import com.qzk.library.annotations.enums.DataTypes;

/**
 * Created by qinzongke on 16/6/21.
 */
public @interface DataType {

    public DataTypes value() default DataTypes.INTEGER;
}
