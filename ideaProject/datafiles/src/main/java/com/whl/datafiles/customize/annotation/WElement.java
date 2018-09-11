package com.whl.datafiles.customize.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Wang HLiang
 * @Description: Create a new file
 * @Date: Created in 15:11 2018-08-01
 * @Modofied:
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface WElement {
    String value();
}
