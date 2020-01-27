package com.one.netease.customokhttp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author diaokaibin@gmail.com on 2020-01-26.
 */
public class MyThreadPool {
    public static void main(String[] args) {


        ExecutorService executorService =  new ThreadPoolExecutor(0,Integer.MAX_VALUE,60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>());

        for (int i = 0; i < 20; i++) {

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println("当前线程执行耗时任务 : " + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        ExecutorService executorService1 = Executors.newCachedThreadPool();

        ExecutorService executorService2= new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread();
                thread.setDaemon(false);
                thread.setName("MyOkHttp Dispatcher");
                return thread;
            }
        }

        );

    }
}
