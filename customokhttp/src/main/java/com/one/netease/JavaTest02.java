package com.one.netease;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;

import javax.net.ssl.SSLSocketFactory;

/**
 * @author diaokaibin@gmail.com on 2020-01-28.
 */
public class JavaTest02 {

    public static void main(String[] args) throws IOException {

        System.out.println("请输入网址");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputPath = br.readLine();

        URL url = new URL("https://" + inputPath);
        String homeName = url.getHost();
        Socket socket = null;
        int port = 0;
        if ("HTTP".equalsIgnoreCase(url.getProtocol())) {
            port = 80;
            socket = new Socket(homeName, port);
        } else if ("HTTPS".equalsIgnoreCase(url.getProtocol())) {
            port = 443;
            socket = SSLSocketFactory.getDefault().createSocket(homeName, port);
        }

        if (socket == null) {
            System.out.println("error");
            return;
        }

        //写出去 请求
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        bw.write("GET / HTTP/1.1\r\n");
        bw.write("Host: " + homeName + "\r\n\r\n");
        bw.flush();

        // 读取数据 响应

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while (true) {

            String readLine = null;
            if ((readLine = bufferedReader.readLine()) != null) {
                System.out.println("响应的数据:" + readLine);

            } else {
                break;
            }


        }


    }

}
