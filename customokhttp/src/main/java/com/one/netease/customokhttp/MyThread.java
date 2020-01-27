package com.one.netease.customokhttp;

/**
 * @author diaokaibin@gmail.com on 2020-01-26.
 */
public class MyThread {
    public static void main(String[] args) {

        Thread thread = new Thread(){

            @Override
            public void run() {
                super.run();

                while (true) {

                    System.out.println(" running  ....  ");
                }
            }
        };

        thread.setDaemon(true);
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
