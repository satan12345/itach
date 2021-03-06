package com.able.concurrency.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 推荐的写法
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface Recommend {
    String value() default "";
}
