package com.one.netease.touchsample;

import com.one.netease.touchsample.listener.OnClickListener;
import com.one.netease.touchsample.listener.OnTouchListener;

/**
 * @author diaokaibin@gmail.com on 2020-01-19.
 */
public class View {

    private int left;
    private int top;
    private int right;
    private int bottom;

    private OnTouchListener mOnTouchListener;
    private OnClickListener mOnClickListener;

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        mOnTouchListener = onTouchListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public View() {
    }

    public View(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }


    /**
     * 能否接收到事件
     * @param x
     * @param y
     * @return
     */
    public boolean isContainer(int x, int y) {
        if (x >= left && x < right && y >= top && y < bottom) {
            return true;
        }
        return false;
    }

    /**
     * 接收分发的代码
     * @param event
     * @return
     *
     * 先分发 再消费
     */
    public boolean dispatchTouchEvent(MotionEvent event) {
        System.out.println("View - dispatchTouchEvent" );

        // 消费
        boolean result = false;
        if (mOnTouchListener != null && mOnTouchListener.onTouch(this, event)) {
                result = true;
        }

        if (!result &&onTouchEvent(event)) {
            result = true;

        }

        // 返回值 响应ViewGroup 的逻辑
        return result;

    }

    private boolean onTouchEvent(MotionEvent event) {
        if (mOnClickListener != null) {
            mOnClickListener.onClick(this);
            return true;
        }
        return false;
    }
}
