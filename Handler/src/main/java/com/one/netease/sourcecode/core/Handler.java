package com.one.netease.sourcecode.core;


/**
 * @author diaokaibin@gmail.com on 2020-01-31.
 */
public class Handler {


    private Looper mLooper;
    private MessageQueue mQueue;

    public Handler() {

        mLooper = Looper.myLooper();
        if (mLooper == null) {
            throw new RuntimeException(
                    "Can't create handler inside thread " + Thread.currentThread()
                            + " that has not called Looper.prepare()");
        }
        mQueue = mLooper.mQueue;
    }



    public void sendMessage(Message message) {
        // 将消息放入消息队列中
        enqueueMessage(message);
    }

    private void enqueueMessage(Message message) {
        //赋值当前Handler
        message.target = this;
        // 使用mQueue,将消息放入
        mQueue.enqueueMessage(message);
    }

    public void dispatchMessage(Message msg) {

        handleMessage(msg);
    }


    /**
     * 给开发者提供的开发API , 用于重写和回调监听
     * @param msg
     */
    public void handleMessage(Message msg) {


    }
}
