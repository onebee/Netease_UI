package com.one.netease.animatesample.animator;

import java.lang.reflect.Method;

/**
 * @author diaokaibin@gmail.com on 2020-01-21.
 *
 * holder 掌控关键帧的集合
 */
public class MyFloatPropertyValuesHolder {


    String mPropertyName;
    Class mValueType;
    Method mSetter = null;
    MyKeyframeSet mKeyframeSet;


    public MyFloatPropertyValuesHolder(String propertyName, float... values) {

        this.mPropertyName = propertyName;
        mValueType = float.class;
        mKeyframeSet = MyKeyframeSet.ofFloat(values);
    }
}
