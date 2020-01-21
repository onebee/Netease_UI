package com.one.netease.animatesample.animator;

import android.animation.FloatEvaluator;
import android.animation.TypeEvaluator;

import java.util.Arrays;
import java.util.List;

/**
 * @author diaokaibin@gmail.com on 2020-01-21.
 */
public class MyKeyframeSet {


    // 类型估值器
    TypeEvaluator mEvaluator;
    MyFloatKeyframe mFirstKeyframe;
    List<MyFloatKeyframe> mKeyframes;

    public MyKeyframeSet(MyFloatKeyframe... keyframes) {
        mKeyframes = Arrays.asList(keyframes);
        mFirstKeyframe = keyframes[0];
        mEvaluator = new FloatEvaluator();
    }

    public static MyKeyframeSet ofFloat(float[] values) {

        int numKeyframe = values.length;
        MyFloatKeyframe keyframes[] = new MyFloatKeyframe[numKeyframe];
        keyframes[0] = new MyFloatKeyframe(0, values[0]);
        for (int i = 1; i < numKeyframe; ++i) {
            keyframes[i] = new MyFloatKeyframe((float) i / (numKeyframe - 1), values[i]);
        }

        return new MyKeyframeSet(keyframes);
    }
}
