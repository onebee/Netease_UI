package com.one.netease.okhttp.chain;

import android.util.Log;

import com.one.netease.okhttp.OkHttpClient2;
import com.one.netease.okhttp.Response2;

import java.io.IOException;

/**
 * @author diaokaibin@gmail.com on 2020-01-28.
 * 重试拦截器
 */
public class ReRequestInterceptor implements Interceptor2 {


    @Override
    public Response2 doNext(Chain2 chain2) throws IOException {

        Log.i("okhttp","我是重试拦截器,执行了");

        ChainManager chainManager = (ChainManager) chain2;
        OkHttpClient2 okHttpClient2 = chainManager.getCall().getOkHttpClient2();

        IOException ioException = null;
        if (okHttpClient2.getRecount() != 0) {
            for (int i = 0; i < okHttpClient2.getRecount(); i++) {

                     Log.i("okhttp","我是重试拦截器,我要返回Response 了");
                try {
                    // 如果没有异常 循环结束
                    Response2 response2 = chain2.getResponse(chainManager.getRequest());
                    return response2;
                } catch (IOException e) {
                    ioException = e;
                }
            }
        }
        throw ioException;
    }
}
