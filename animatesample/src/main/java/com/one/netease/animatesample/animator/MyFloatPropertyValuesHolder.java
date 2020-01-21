package com.one.netease.animatesample.animator;

import android.view.View;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
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
    MyKeyframeSet mKeyframes;


    public MyFloatPropertyValuesHolder(String propertyName, float... values) {

        this.mPropertyName = propertyName;
        mValueType = float.class;
        mKeyframes = MyKeyframeSet.ofFloat(values);
    }

    public void setupSetter(WeakReference<View> target) {

        char firstLetter = Character.toUpperCase(mPropertyName.charAt(0));
        String theRest = mPropertyName.substring(1);
        String methodName = "set" + firstLetter + theRest;
        try {
            mSetter = View.class.getMethod(methodName, float.class);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }

    public void setAnimatedValue(View target, float fraction) {
      Object value =  mKeyframes.getValue(fraction);

        try {
            mSetter.invoke(target, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
