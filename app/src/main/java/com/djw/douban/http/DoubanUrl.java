package com.djw.douban.http;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by JasonDong
 * <p>
 * on 2017/3/23.
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface DoubanUrl {

}
