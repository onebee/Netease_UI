package com.one.netease.sourcecode.core;


/**
 * @author diaokaibin@gmail.com on 2020-01-31.
 */
public class Looper {

    public MessageQueue mQueue;

    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();


    private Looper() {
        mQueue = new MessageQueue();
    }

    public static void prepare() {
        // 主线程只有唯一一个 Looper 对象
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }

        // 应用启动时, 初始化赋值
        sThreadLocal.set(new Looper());
    }

    /**
     * 轮询,提取消息
     */
    public static void loop() {

        // 从全局ThreadLocalMap 获取唯一: Looper 对象
        Looper me = myLooper();

        // 从Looper 对象中获取全局唯一消息队列MessageQueue 对象
        final MessageQueue queue = me.mQueue;

        while (true) {

            Message msg = queue.next();
            if (msg != null) {
                msg.target.dispatchMessage(msg);
            }
        }


    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }
}
