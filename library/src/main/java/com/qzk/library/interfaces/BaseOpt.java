package com.qzk.library.interfaces;

import java.util.List;

/**
 * 类名：BaseOpt
 * 描述：
 * 包名： com.qzk.library.abstracts
 * 项目名：DataProvider
 * Created by qinzongke on 6/24/16.
 */
public interface BaseOpt {

    List<Object> baseFindAll();

    Object baseFindFist();

    long baseInsert();

    void baseUpdate();

    void baseDelete();
}
