package com.one.netease.animatesample.animator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diaokaibin@gmail.com on 2020-01-21.
 */
public class VSYNCManager {

    private static final VSYNCManager ourInstance = new VSYNCManager();

    public static VSYNCManager getOurInstance() {
        return ourInstance;
    }


    private VSYNCManager() {
        new Thread(runnable).start();
    }


    private List<AnimatorFrameCallBack> list = new ArrayList<>();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true) {

                for (AnimatorFrameCallBack animatorFrameCallBack : list) {
                    animatorFrameCallBack.doAnimationFrame(System.currentTimeMillis());
                }
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    interface AnimatorFrameCallBack {

        boolean doAnimationFrame(long currentTime);
    }

}
