package com.one.netease.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

import javax.net.ssl.SSLSocketFactory;

/**
 * @author diaokaibin@gmail.com on 2020-01-28.
 */
public class Test01 {

    private static final String PATH = "https://wanandroid.com/wxarticle/chapters/json  \n";

    public static void main(String[] args) {
        urlAction();
//        socketHTTPS();
    }

    private static void urlAction() {
        try {
            URL url = new URL(PATH);

            System.out.println("    "+ url.getProtocol());
            System.out.println("    "+ url.getHost());
            System.out.println("    "+ url.getFile());
            System.out.println("    "+ url.getQuery());
            System.out.println("    "+ url.getPort()+"  --  " + url.getDefaultPort());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }


    private static void socketHTTPS() {


        try {
//            Socket socket = new Socket("www.baidu.com",443);

            //ssl 握手 访问 https 的 socket 客户端
            Socket socket = SSLSocketFactory.getDefault().createSocket("www.baidu.com",443);

            //写出去 请求
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            bw.write("GET / HTTP/1.1\r\n");
            bw.write("Host: www.baidu.com\r\n\r\n");
            bw.flush();

            // 读取数据 响应

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {

            String readLine = null;
                if ((readLine = br.readLine()) != null) {
                    System.out.println("响应的数据:" + readLine);

                } else {
                    break;
                }


            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
