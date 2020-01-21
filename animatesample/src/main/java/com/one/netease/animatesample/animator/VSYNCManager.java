package com.one.netease.animatesample.animator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diaokaibin@gmail.com on 2020-01-21.
 */
public class VSYNCManager {

    private static final VSYNCManager ourInstance = new VSYNCManager();

    public static VSYNCManager getInstance() {
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

                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (AnimatorFrameCallBack animatorFrameCallBack : list) {
                    animatorFrameCallBack.doAnimationFrame(System.currentTimeMillis());
                }
            }
        }
    };

    public void add(AnimatorFrameCallBack animationFrameCallback) {
        list.add(animationFrameCallback);
    }

    interface AnimatorFrameCallBack {

        boolean doAnimationFrame(long currentTime);
    }

}
