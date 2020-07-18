package com.chuanchiai.dynamicRouting;

import com.chuanchiai.enums.DataSourceEnum;

import java.lang.annotation.*;

/**
 * Created by chuanchiai on 2020/7/18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataSourceSelector {
    DataSourceEnum name() default DataSourceEnum.MASTER;
    boolean clear() default true;
}
