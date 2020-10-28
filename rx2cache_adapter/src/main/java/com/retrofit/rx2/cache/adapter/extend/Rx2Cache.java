package com.retrofit.rx2.cache.adapter.extend;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface Rx2Cache {
    Rx2CacheType CacheType() default Rx2CacheType.NO_CACHE;
}
