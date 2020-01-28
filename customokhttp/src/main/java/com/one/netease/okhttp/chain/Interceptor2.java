package com.one.netease.okhttp.chain;

import com.one.netease.okhttp.Response2;

import java.io.IOException;

/**
 * @author diaokaibin@gmail.com on 2020-01-28.
 */
public interface Interceptor2 {

    Response2 doNext(Chain2 chain) throws IOException;
}
