package com.one.netease.sourcecode;

import com.one.netease.sourcecode.core.Handler;
import com.one.netease.sourcecode.core.Looper;
import com.one.netease.sourcecode.core.Message;

import org.junit.Test;

/**
 * @author diaokaibin@gmail.com on 2020-01-31.
 */
public class ActivityThread {


    @Test
    public void main() {


        // 创建全局唯一的,主线程Looper 对象,以及MessageQueue 消息队列对象
        Looper.prepare();

        //模拟Activity 中,创建Handler 对象
        final Handler handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                System.out.println(msg.obj.toString());
            }
        };


        // 消费消息,回调方法(接口方法)

        // 子线程发送消息
        new Thread(new Runnable() {
            @Override
            public void run() {

                Message message = new Message();
                message.obj = "hello one bit !";
                handler.sendMessage(message);

            }
        }).start();

        // 轮询, 取出消息
        Looper.loop();


    }
}
