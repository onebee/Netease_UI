package com.one.netease.okhttp.chain;

import com.one.netease.okhttp.RealCall2;
import com.one.netease.okhttp.Request2;
import com.one.netease.okhttp.Response2;

import java.io.IOException;
import java.util.List;


/**
 * @author diaokaibin@gmail.com on 2020-01-28.
 * <p>
 * 责任节点任务管理器
 */
public class ChainManager implements Chain2 {

    private final List<Interceptor2> interceptors;
    private int index;
    private final Request2 request;  // 请求头拦截器 更新request
    private final RealCall2 call;

    public List<Interceptor2> getInterceptors() {
        return interceptors;
    }

    public int getIndex() {
        return index;
    }

    public RealCall2 getCall() {
        return call;
    }

    public ChainManager(List<Interceptor2> interceptors, int index, Request2 request, RealCall2 call) {
        this.interceptors = interceptors;
        this.index = index;
        this.request = request;
        this.call = call;
    }


    @Override
    public Request2 getRequest() {
        return request;
    }

    @Override
    public Response2 getResponse(Request2 request2) throws IOException {
        if (index >= interceptors.size()) throw new AssertionError();


        if (interceptors.isEmpty()) throw new IOException("interceptors is empty");
        ChainManager manager = new ChainManager(interceptors, index++, request, call);

        // 取出第一个拦截器
        Interceptor2 interceptor2 = interceptors.get(index);
        Response2 response2 = interceptor2.doNext(manager);



        return response2;
    }
}
