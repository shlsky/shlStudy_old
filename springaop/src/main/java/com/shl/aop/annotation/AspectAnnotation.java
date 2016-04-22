package com.shl.aop.annotation;

import java.lang.annotation.*;

/**
 * Created by jackson on 16/1/28.
 */
@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited

public @interface AspectAnnotation {
    int second() default 0;
}
