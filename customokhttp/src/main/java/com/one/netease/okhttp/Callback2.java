package com.one.netease.okhttp;

import java.io.IOException;

/**
 * @author diaokaibin@gmail.com on 2020-01-27.
 */
public interface Callback2 {

    void onFailure(Call2 call, IOException e);

    void onResponse(Call2 call, Response2 response) throws IOException;
}
