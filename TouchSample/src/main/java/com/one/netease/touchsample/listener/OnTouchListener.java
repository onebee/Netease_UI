package com.one.netease.touchsample.listener;

import com.one.netease.touchsample.MotionEvent;
import com.one.netease.touchsample.View;

/**
 * @author diaokaibin@gmail.com on 2020-01-19.
 */
public interface OnTouchListener {

    boolean onTouch(View view, MotionEvent event);
}
