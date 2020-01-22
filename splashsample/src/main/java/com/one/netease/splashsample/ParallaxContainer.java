package com.one.netease.splashsample;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * @author diaokaibin@gmail.com on 2020-01-21.
 */
public class ParallaxContainer extends FrameLayout implements ViewPager.OnPageChangeListener {


    private List<ParallaxFragment> fragments;

    public ParallaxContainer(@NonNull Context context) {
        super(context);
    }

    public ParallaxContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    public void setUp(int... childIds) {

        fragments = new ArrayList<>();
        for (int i = 0; i < childIds.length; i++) {

            ParallaxFragment f = new ParallaxFragment();

            Bundle args = new Bundle();
            // Fragment 中需要加载的布局文件ID
            args.putInt("layoutId",childIds[i]);
            f.setArguments(args);
            fragments.add(f);

        }


        ViewPager vp = new ViewPager(getContext());
        vp.setId(R.id.parallax_pager);

        SplashActivity activity = (SplashActivity) getContext();
        ParallaxPagerAdapter adapter = new ParallaxPagerAdapter(activity.getSupportFragmentManager(),fragments);
        vp.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        vp.setAdapter(adapter);
        addView(vp,0);

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
