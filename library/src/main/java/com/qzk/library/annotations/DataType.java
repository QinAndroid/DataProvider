package com.qzk.library.annotations;

import com.qzk.library.annotations.enums.DataTypes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by qinzongke on 16/6/21.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DataType {

    public DataTypes value() default DataTypes.INTEGER;
}
