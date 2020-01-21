package com.one.netease.animatesample.animator;

/**
 * @author diaokaibin@gmail.com on 2020-01-21.
 *
 * 关键帧 保存这某一时刻具体状态  初始化的时候完成
 */
public class MyFloatKeyframe {

    float mFraction;
    Class mValueType;
    float mValue;


    public MyFloatKeyframe(float fraction, float value) {
        mFraction = fraction;
        mValue = value;
        mValueType = float.class;
    }

    public float getFraction() {
        return mFraction;
    }

    public float getValue() {
        return mValue;
    }

    public void setValue(float value) {
        mValue = value;
    }
}
