package com.one.netease.splashsample;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @author diaokaibin@gmail.com on 2020-01-21.
 */
public class ParallaxPagerAdapter extends FragmentPagerAdapter {


    public ParallaxPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public ParallaxPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
