package com.one.handlersamploe;

import org.junit.Test;

import androidx.annotation.Nullable;

/**
 * @author diaokaibin@gmail.com on 2020/6/1.
 */
public class ThreadLocalTest {


    @Test
    public void test(){

        ThreadLocal<String> local = new ThreadLocal<String>(){

            @Nullable
            @Override
            protected String initialValue() {


                return "字节跳动";
            }

        };

        String s = local.get();
        System.out.println(s);

    }
}
