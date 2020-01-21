package com.one.netease.splashsample;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @author diaokaibin@gmail.com on 2020-01-21.
 */
public class ParallaxPagerAdapter extends FragmentPagerAdapter {

    private List<ParallaxFragment> mFragments;

    public ParallaxPagerAdapter(@NonNull FragmentManager fm,List<ParallaxFragment> fragments) {
        super(fm);
        this.mFragments=fragments;
    }

    public ParallaxPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
