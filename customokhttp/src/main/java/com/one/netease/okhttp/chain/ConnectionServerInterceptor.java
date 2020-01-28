package com.one.netease.okhttp.chain;

import com.one.netease.okhttp.Request2;
import com.one.netease.okhttp.Response2;
import com.one.netease.okhttp.SocketRequestServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author diaokaibin@gmail.com on 2020-01-28.
 * 连接服务拦截器
 */
public class ConnectionServerInterceptor implements Interceptor2 {
    @Override
    public Response2 doNext(Chain2 chain) throws IOException {


        //解析Request
        SocketRequestServer srs = new SocketRequestServer();
        Request2 request2 = chain.getRequest();  // 更新后的request

        Socket socket = new Socket(srs.getHost(request2), srs.getPort(request2));

        //todo 请求


        //output
        OutputStream os = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os));
        String requestAll = srs.getRequestHeaderALL(request2);

        System.out.println("requestAll : " + requestAll);
        bufferedWriter.write(requestAll); // 给服务器发送请求--请求头信息 所有的

        bufferedWriter.flush();// 真正的写出去



        //todo 响应
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        Response2 response2 = new Response2();
        // todo 取出 响应码
        String readLine = bufferedReader.readLine();//读取第一行 响应头信息
        // 服务器响应的: HTTP/1.1 200 OK
        String[] strings = readLine.split("  ");
        response2.setStatusCode(Integer.parseInt(strings[1]));

        //todo 取出响应体, 只要是空行下面的,就是响应体
        String readerLine = null;
        try {
            while ((readerLine = bufferedReader.readLine()) != null) {
                if ("".equals(readerLine)) {
                    // 读到空行了, 就代表下面就是响应体了
                    response2.setBody(bufferedReader.readLine());
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return response2;
    }
}
