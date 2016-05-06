package com.shl.easyhttp.annotations;

import java.lang.annotation.*;

/**
 * Created by sky on 4/21/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface HttpComponent {

    long connectTimeout() default 15000;

    long readTimeout() default 15000;

    String baseUrl() default "";

}
