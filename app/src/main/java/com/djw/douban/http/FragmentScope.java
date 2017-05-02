package com.djw.douban.http;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by JasonDong
 * <p>
 * on 2017/3/23.
 */

@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
