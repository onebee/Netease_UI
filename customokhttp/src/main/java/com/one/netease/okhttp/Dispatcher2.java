package com.one.netease.okhttp;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author diaokaibin@gmail.com on 2020-01-28.
 */
public class Dispatcher2 {

    private int maxRequests = 64;// 同时访问任务,最大限制64个
    private int maxRequestsPerHost = 5;// 同时访问同一个服务器域名,最大限制5个
    private Deque<RealCall2.AsyncCall2> runningAsyncCalls = new ArrayDeque<>();// 存储运行的队列
    private Deque<RealCall2.AsyncCall2> readyAsyncCalls = new ArrayDeque<>();// 存储等待的队列



    public void enqueue(RealCall2.AsyncCall2 call) {

        //同时运行的队列数,必须小于配置的64 && 同时访问同一个服务器域名 不能超过5个
        if (runningAsyncCalls.size() < maxRequests && runningCallsForHost(call) < maxRequestsPerHost) {
            // 先把任务加入到运行队列中
            runningAsyncCalls.add(call);
            // 然后执行
            executorService().execute(call);
        } else {
            // 加入到等待队列
            readyAsyncCalls.add(call);
        }

    }

    /**
     * 缓存方案
     * @return
     */
    public ExecutorService executorService() {
        ExecutorService executorService = new ThreadPoolExecutor(0,
                Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread();
                        thread.setName("自定义的线程...");
                        thread.setDaemon(false);
                        return thread;
                    }
                }
        );
        return executorService;
    }

    /**
     * 判断 AsyncCall2中的Host, 在运行的队列中, 计数,然后放回
     *
     * @param call  Request 的封装 有Host
     * @return
     */
    private int runningCallsForHost(RealCall2.AsyncCall2 call) {
        int count =0;
        if (runningAsyncCalls.isEmpty()) {
            return 0;
        }

        SocketRequestServer srs = new SocketRequestServer();

        /**
         * 遍历运行队列里面的所有任务,取出任务host== call.host   +1
         */
        for (RealCall2.AsyncCall2 runningAsyncCall : runningAsyncCalls) {
            if (srs.getHost(runningAsyncCall.getRequest()).equals(call.getRequest())) {

                count++;
            }
        }

        return count;
    }
}
