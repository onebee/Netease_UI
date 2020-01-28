package com.one.netease.okhttp;

import com.one.netease.okhttp.chain.ChainManager;
import com.one.netease.okhttp.chain.Interceptor2;
import com.one.netease.okhttp.chain.ReRequestInterceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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


    public OkHttpClient2 getOkHttpClient2() {
        return mOkHttpClient2;
    }

    public boolean isExecuted() {
        return executed;
    }

    public Request2 getRequest2() {
        return request2;
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

                Response2 response = getResponseWithInterceptorChain();
                // 如果用户取消了请求,回调给用户
                if (mOkHttpClient2.getCanceled()) {
                    signalledCallback = true;
                    callback2.onFailure(RealCall2.this, new IOException("用户取消了Canceled"));
                } else {
                    signalledCallback = true;
                    callback2.onResponse(RealCall2.this,response);
                }
            } catch (Exception e) {

                // 责任的划分
                if (signalledCallback) {// 回调给用户了 , 是用户操作的时候报错的
                    System.out.println("用户在使用过程中出错了");

                } else {
                    callback2.onFailure(RealCall2.this, new IOException("OkHttp getResponseWithInterceptorChain() 方法出错 "+ e.toString()));

                }
            }finally {
                //回收处理♻️
                mOkHttpClient2.dispatcher().finished(this);

            }
        }

        private Response2 getResponseWithInterceptorChain() throws IOException {
//            Response2 response2 = new Response2();
//            response2.setBody("流程走通");
//            return response2;

            List<Interceptor2> interceptor2List = new ArrayList<>();
            interceptor2List.add(new ReRequestInterceptor());  // 重试拦截器

            ChainManager chainManager = new ChainManager(interceptor2List,0,request2,RealCall2.this);
           return chainManager.getResponse(request2);// 最终返回的
        }
    }

}
