package com.one.netease.sourcecode.core;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author diaokaibin@gmail.com on 2020-01-31.
 *
 * 消息队列
 */
public class MessageQueue {

    //阻塞队列
    BlockingQueue<Message> blockingQueue = new ArrayBlockingQueue<>(50);



    // 将Message 消息对象存入 阻塞队列
    public void enqueueMessage(Message message) {
        try {
            blockingQueue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 从消息队列中取出消息
     * @return
     */
    public Message next() {
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
