package com.one.netease.okhttp.chain;

import com.one.netease.okhttp.Request2;
import com.one.netease.okhttp.Response2;

import java.io.IOException;

/**
 * @author diaokaibin@gmail.com on 2020-01-28.
 */
public interface Chain2 {

    Request2 getRequest();
    Response2 getResponse(Request2 request2) throws IOException;

}
