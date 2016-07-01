package com.qzk.library.annotations;

import com.qzk.library.annotations.enums.DataTypes;
import com.qzk.library.annotations.enums.PrimaryKeyTypes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by qinzongke on 16/6/21.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface PrimaryKey {

    public PrimaryKeyTypes value() default PrimaryKeyTypes.AUTOINCREMENT;
}
