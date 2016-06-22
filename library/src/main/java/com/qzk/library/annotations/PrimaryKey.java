package com.qzk.library.annotations;

import com.qzk.library.annotations.enums.DataTypes;
import com.qzk.library.annotations.enums.PrimaryKeyTypes;

/**
 * Created by qinzongke on 16/6/21.
 */
public @interface PrimaryKey {

    public PrimaryKeyTypes value() default PrimaryKeyTypes.AUTOINCREMENT;
}
