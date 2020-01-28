package com.one.netease.okhttp.chain;

import com.one.netease.okhttp.Request2;
import com.one.netease.okhttp.RequestBody2;
import com.one.netease.okhttp.Response2;
import com.one.netease.okhttp.SocketRequestServer;

import java.io.IOException;
import java.util.Map;

/**
 * @author diaokaibin@gmail.com on 2020-01-28.
 *
 * 请求头拦截器
 */
public class RequestHeaderInterceptor implements Interceptor2 {



//    POST /wxarticle/chapters/json HTTP/1.1
//    Content-Length: 0
//    Host: wanandroid.com
//    Content-Type: application/json

    @Override
    public Response2 doNext(Chain2 chain) throws IOException {

        //拼接请求头 指请求集
        ChainManager chainManager = (ChainManager)chain;
        Request2 request = chainManager.getRequest();
        Map<String, String> headerList = request.getHeaderList();
        headerList.put("Host", new SocketRequestServer().getHost(request));
        if ("POST".equalsIgnoreCase(request.getRequestMethod())) {
            headerList.put("Content-Length",request.getRequestBody2().getBody().length()+"");
            headerList.put("Content-Type", RequestBody2.TYPE);


        } else {

        }
        return chain.getResponse(request);
    }
}
