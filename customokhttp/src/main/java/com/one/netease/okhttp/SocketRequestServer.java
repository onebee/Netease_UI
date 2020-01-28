package com.one.netease.okhttp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * @author diaokaibin@gmail.com on 2020-01-28.
 * 解析Request
 * 请求头 的请求行
 * 请求域名
 * 端口号
 * 资源路径file
 */
public class SocketRequestServer {


    private final String K=" ";
    private final String VIERSION = "HTTP/1.1";
    private final String GRGN = "\r\n";

    /**
     * 通过request 对象,寻找到域名HOST
     * @param request2
     * @return
     */
    public String getHost(Request2 request2) {

        try {
            URL url = new URL(request2.getUrl());
            return url.getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getPort(Request2 request2) {
        try {
            URL url = new URL(request2.getUrl());
            int port = url.getPort();
            return port==-1? url.getDefaultPort():port;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取请求头的所有信息
     * @param request2
     * @return
     */
    public String getRequestHeaderALL(Request2 request2) {
        // 得到请求方式
        URL url = null;
        try {
            url = new URL(request2.getUrl());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String file = url.getFile();
        // 拼接请求头 的请求行 GET/V3/weather/xxxx
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(request2.getRequestMethod())
                .append(K)
                .append(file)
                .append(K)
                .append(VIERSION)
                .append(GRGN);


        //todo 获取请求集 进行拼接
        if (!request2.getHeaderList().isEmpty()) {
            Map<String, String> mapList = request2.getHeaderList();
            for (Map.Entry<String, String> entry : mapList.entrySet()) {
                stringBuffer.append(entry.getKey())
                        .append(":").append(K)
                        .append(entry.getValue())
                        .append(GRGN);
            }

            //拼接空行,代表下面的POST ,请求体了
            stringBuffer.append(GRGN);
        }

        // todo POST 请求才有 请求体的拼接
        if ("POST".equalsIgnoreCase(request2.getRequestMethod())) {
            stringBuffer.append(request2.getRequestBody2().getBody()).append(GRGN);
        }

        return stringBuffer.toString();
    }

    /***
     * 获得urlString 是http or https
     * @param urlString
     * @return
     */
    public String queryHttpOrHttps(String urlString) {
        try {
            URL url = new URL(urlString);
            String protocol = url.getProtocol();
            return protocol;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
