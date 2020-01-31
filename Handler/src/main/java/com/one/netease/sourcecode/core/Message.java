package com.one.netease.sourcecode.core;

/**
 * @author diaokaibin@gmail.com on 2020-01-31.
 */
public class Message {


    // 标识
    public int what;

    // 消息内容
    public Object obj;

    // Handler 对象
    public Handler target;


    public Message() {
    }

    public Message(Object obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "Message{" +
                "obj=" + obj +
                '}';
    }
}
