package com.qzk.library.entitys;

import com.qzk.library.annotations.enums.DataTypes;
import com.qzk.library.helpers.ObjectHelper;
import com.qzk.library.utils.LogUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 类名：Model
 * 描述：
 * 包名： com.qzk.library.entitys
 * 项目名：DataProvider
 * Created by qinzongke on 6/24/16.
 */
public abstract class Model<M extends Model> {

    private static final int DEFALUTVALUE = -1111;

    public Model() {
        Class clazz = getUsefulClass();
        Object object = this;
        Field[] fields = ObjectHelper.getObjectFields(clazz);
        for (Field field:fields) {
            if(field.isSynthetic()){
                continue;
            }
            field.setAccessible(true);
            DataTypes type = ObjectHelper.getFieldType(field);
            if(DataTypes.INTEGER.equals(type) || DataTypes.DOUBLE.equals(type)||DataTypes.FLOAT.equals(type)||DataTypes.LONG.equals(type)){
                ObjectHelper.setObjectValue(field,this,DEFALUTVALUE);
            }
        }
    }

    private Class<? extends Model> getUsefulClass() {
        Class c = getClass();
        return c.getName().indexOf("EnhancerByCGLIB") == -1 ? c : c.getSuperclass();
    }


}
