package com.djw.douban.base;

/**
 * Created by JasonDong
 * <p>
 * on 2017/3/23.
 */
public class ApiException extends Exception {
    public ApiException(String msg) {
        super(msg);
    }
}