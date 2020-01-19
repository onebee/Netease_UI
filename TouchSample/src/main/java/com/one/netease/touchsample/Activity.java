package com.one.netease.touchsample;

import com.one.netease.touchsample.listener.OnClickListener;
import com.one.netease.touchsample.listener.OnTouchListener;

public class Activity {


    public static void main(String[] args) {

        ViewGroup viewGroup = new ViewGroup(0,0,1080,1920);
        viewGroup.setName("顶级容器");

        ViewGroup viewGroup1 = new ViewGroup(0,0,500,500);
        viewGroup1.setName("第二级容器");

        View view = new View(0,0,200,200);

        viewGroup1.addView(view);

        viewGroup.addView(viewGroup1);


        viewGroup.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                System.out.println("顶级容器的 OnTouch 事件");
                return false;
            }
        });


        viewGroup1.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                System.out.println("第二级 ViewGroup 的OnTouch 事件");
                return false;
            }
        });

        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("View的Click 事件");

            }
        });

        view.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                System.out.println("View的OnTouch 事件");
                return true;
            }
        });


        MotionEvent motionEvent = new MotionEvent(100, 100);
        motionEvent.setActionMasked(MotionEvent.ACTION_DOWN);


        // 顶级容器分发
        viewGroup.dispatchTouchEvent(motionEvent);
    }
}
