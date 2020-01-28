package com.one.netease.network;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author diaokaibin@gmail.com on 2020-01-28.
 */
public class Test01 {

    private static final String PATH = "https://wanandroid.com/wxarticle/chapters/json  \n";

    public static void main(String[] args) {
        urlAction();
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
}
