package com.one.netease.sourcecode;

import org.junit.Test;

import androidx.annotation.Nullable;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
//    @Test
//    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//    }


    @Test
    public void test() {
        // 创建本地线程(主线程)
        final ThreadLocal<String> threadLocal = new ThreadLocal<String>() {

            @Nullable
            @Override
            protected String initialValue() {

                // 重写初始化方法,默认返回null , 如果ThreadLocalMap 拿不到值再调用初始化方法
                return "ONE BIT";
            }

        };

        // 从ThreadLocalMap 中获取String 值,key 是主线程
        System.out.println(" 主线程 threadLocal : " + threadLocal.get());


        //----------------------thread-0
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 从ThreadLocalMap 中获取key : thread-0 的值?
                String value1 = threadLocal.get();

                System.out.println(" thread-0  threadLocal : " + value1);

                threadLocal.set("two bit");
                System.out.println(" thread-0  threadLocal : " + threadLocal.get());

                // 使用完成 建议 remove(), 避免大量无意义的内存占用
                threadLocal.remove();

            }
        });

        thread.start();


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {


                String value = threadLocal.get();

                System.out.println(" thread-1  threadLocal : " + value);


            }
        });

        thread2.start();

    }
}