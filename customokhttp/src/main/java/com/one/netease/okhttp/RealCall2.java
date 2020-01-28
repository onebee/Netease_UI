package com.one.netease.okhttp;

import java.io.IOException;

import okhttp3.Response;

/**
 * @author diaokaibin@gmail.com on 2020-01-28.
 */
public class RealCall2 implements Call2 {


    private OkHttpClient2 mOkHttpClient2;
    private boolean executed;
    private Request2 request2;


    public RealCall2(OkHttpClient2 okHttpClient2, Request2 request2) {

        this.mOkHttpClient2 = okHttpClient2;
        this.request2 = request2;
    }


    @Override
    public void enqueue(Callback2 responseCallback) {
        // 不能被重复执行
        synchronized (this) {
            if (executed) {
                executed = true;
                throw new IllegalStateException("Already Executed");
            }
        }

        mOkHttpClient2.dispatcher().enqueue(new AsyncCall2(responseCallback));

    }


    public final class AsyncCall2 implements Runnable{
        private Callback2 callback2;


        public Request2 getRequest() {
            return RealCall2.this.request2;
        }

        public AsyncCall2(Callback2 callback2) {
            this.callback2=callback2;
        }

        @Override
        public void run() {
            // 耗时操作
            boolean signalledCallback = false;
            try {

                Response response = getResponseWithInterceptorChain();
                // 如果用户取消了请求,回调给用户
            } catch (Exception e) {

            }
        }

        private Response getResponseWithInterceptorChain() throws IOException {

            return null;
        }
    }

}
