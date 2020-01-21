package com.one.netease.animatesample.animator;

        import android.view.View;

import java.lang.ref.WeakReference;

/**
 * @author diaokaibin@gmail.com on 2020-01-21.
 */
public class MyObjectAnimator implements VSYNCManager.AnimatorFrameCallBack {


    private static final String TAG = "tuch";
    long mStartTime =-1;



    private long mDuration = 0;
    private WeakReference<View> target;

    private float index = 0;



    private TimeInterpolator mInterpolator;

    MyFloatPropertyValuesHolder mFloatPropertyValuesHolder;

    private MyObjectAnimator(View view, String propertyName, float... values) {

        target = new WeakReference<>(view);
        mFloatPropertyValuesHolder = new MyFloatPropertyValuesHolder(propertyName,values);
    }

    public static MyObjectAnimator ofFloat(View view, String propertyName, float... values) {
        MyObjectAnimator anim = new MyObjectAnimator(view, propertyName,values);
        return anim;
    }

    public void start() {

        mFloatPropertyValuesHolder.setupSetter(target);
        mStartTime = System.currentTimeMillis();
        VSYNCManager.getInstance().add(this);

        // 初始化


    }

    public void setInterpolator(TimeInterpolator interpolator) {
        mInterpolator = interpolator;
    }

    public void setDuration(long duration) {
        mDuration = duration;
    }

    /**
     * 每隔16毫秒
     * @param currentTime
     * @return
     */
    @Override
    public boolean doAnimationFrame(long currentTime) {

        float total = mDuration/16;

        // 执行百分比  index++/total
        float fraction = (index++)/total;
        if (mInterpolator != null) {
             fraction = mInterpolator.getInterpolation(fraction);
        }

        if (index >= total) {
            index =0;
        }
        mFloatPropertyValuesHolder.setAnimatedValue(target.get(), fraction);


        return false;
    }
}
