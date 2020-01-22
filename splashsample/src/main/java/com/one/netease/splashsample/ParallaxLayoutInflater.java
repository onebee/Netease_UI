package com.one.netease.splashsample;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author diaokaibin@gmail.com on 2020-01-21.
 */
public class ParallaxLayoutInflater extends LayoutInflater {

    public static final String TAG = "bit>>>";

    private ParallaxFragment fragment;


    protected ParallaxLayoutInflater(LayoutInflater original, Context newContext, ParallaxFragment fragment) {
        super(original, newContext);
        this.fragment = fragment;
        setFactory2(new ParallaxFactory(this));
    }


    protected ParallaxLayoutInflater(LayoutInflater original, Context newContext) {
        super(original, newContext);
    }


    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return new ParallaxLayoutInflater(this, newContext, fragment);
    }


    class ParallaxFactory implements Factory2 {

        private LayoutInflater inflater;

        public ParallaxFactory(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        @Nullable
        @Override
        public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {

            View view = null;
//            createMyView(name, context, attrs);

            Log.i(TAG, "onCreateView : name - " + name);
            return null;
        }

//        private View createMyView(String name, Context context, AttributeSet attrs) {
//
//
//        }

        @Nullable
        @Override
        public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
            return null;
        }
    }
}
