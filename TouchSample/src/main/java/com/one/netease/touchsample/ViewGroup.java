package com.one.netease.touchsample;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diaokaibin@gmail.com on 2020-01-19.
 */
public class ViewGroup extends View {


    List<View> childList = new ArrayList<>();

    // 存放当前容器里面所有view , 频繁查找 放在数组里面 比较快
    private View[] mChildren = new View[0];

    public ViewGroup(int left, int top, int right, int bottom) {
        super(left, top, right, bottom);

    }


    public void addView(View view) {
        if (view == null) return;
        childList.add(view);
        mChildren = (View[]) childList.toArray(new View[childList.size()]);
    }


    private TouchTarget mFirstTouchTarget;

    /**
     * 事件分发的入口
     *
     * @param event
     * @return
     */
    public boolean dispatchTouchEvent(MotionEvent event) {

        System.out.println(name + " dispatchTouchEvent  ");
        boolean intercepted = onInterceptTouchEvent(event);
        // TouchTarget 模式  内存缓存
        TouchTarget newTouchTarget = null;
        boolean handled = false;

        int actionMasked = event.getActionMasked();
        if (actionMasked != MotionEvent.ACTION_CANCEL && !intercepted) {

            // Down 事件
            if (actionMasked == MotionEvent.ACTION_DOWN) {
//
                final View[] children = mChildren;
                for (int i = children.length - 1; i >= 0; i--) {
                    View child = mChildren[i];
                    // View 能够接受到事件


                    if (!child.isContainer(event.getX(), event.getY())) {
                        continue;
                    }

                    // 能够接收事件的 child 分发给他
                    if (dispatchTransformedTouchEvent(event, child)) {

                        handled = true;
                        //View[] 采取了Message 的方式进行,链表结构
                        newTouchTarget = addTouchTarget(child);
                        break;
                    }
                }


            }

            // 当前ViewGroup dispatchTransformedTouchEvent
            if (mFirstTouchTarget == null) {// 说明没人View 去接收当前事件

                handled = dispatchTransformedTouchEvent(event, null);
            }


        }

        return handled;
    }

    private TouchTarget addTouchTarget(View child) {
        final TouchTarget target = TouchTarget.obtain(child);
        target.next = mFirstTouchTarget;
        mFirstTouchTarget = target;
        return target;
    }

    private static final class TouchTarget {

        public View child; // 当前缓存的View

        // 回收池(一个对象)
        private static TouchTarget sRecycleBin;


        public TouchTarget next;

        // size
        private static int sRecycledCount;


        private static final Object sRecycleLock = new Object[0];

        public static TouchTarget obtain(View child) {
            TouchTarget target;
            synchronized (sRecycleLock) {
                if (sRecycleBin == null) {
                    target = new TouchTarget();
                } else {
                    target = sRecycleBin;
                }
                sRecycleBin = target.next;
                sRecycledCount--;
                target.next = null;

            }

            target.child = child;

            return target;

        }

        public void recycle() {
            if (child == null) {
                throw new IllegalStateException("已经被回收了");
            }

            synchronized (sRecycleLock) {
                if (sRecycledCount < 32) {
                    next = sRecycleBin;
                    sRecycleBin = this;
                    sRecycledCount += 1;
                }


            }
        }


    }


    /**
     * 分发处理 子控件View
     *
     * @param event
     * @param child
     * @return
     */
    private boolean dispatchTransformedTouchEvent(MotionEvent event, View child) {
         boolean handled = false;
        if (child != null) {

            handled = child.dispatchTouchEvent(event);
        } else {

            handled = super.dispatchTouchEvent(event);
        }
        return handled;
    }


    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    private String name;

    @Override
    public String toString() {
        return "ViewGroup{" +
                "name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
