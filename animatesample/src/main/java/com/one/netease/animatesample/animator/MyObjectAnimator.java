package com.one.netease.animatesample.animator;

        import android.view.View;

import java.lang.ref.WeakReference;

/**
 * @author diaokaibin@gmail.com on 2020-01-21.
 */
public class MyObjectAnimator {


    private static final String TAG = "tuch";
    long mStartTime =-1;
    private long mDuration = 0;
    private WeakReference<View> target;

    MyFloatPropertyValuesHolder mFloatPropertyValuesHolder;

    private MyObjectAnimator(View view, String propertyName, float... values) {

        target = new WeakReference<>(view);
        mFloatPropertyValuesHolder = new MyFloatPropertyValuesHolder(propertyName,values);
    }

    public static MyObjectAnimator ofFloat(View view, String propertyName, float... values) {
        MyObjectAnimator anim = new MyObjectAnimator(view, propertyName,values);
        return anim;
    }
}
