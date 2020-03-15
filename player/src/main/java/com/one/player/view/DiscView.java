package com.one.player.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.one.player.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.ViewPager;

/**
 * @author diaokaibin@gmail.com on 2020/3/13.
 */
public class DiscView extends RelativeLayout {

    //    Pager的View
    private List<View> mDiscLayouts = new ArrayList<>();

    private List<Integer> mMusicDatas = new ArrayList<>();

    private List<ObjectAnimator> mDiscAnimators = new ArrayList<>();
    ImageView musicNeedle;
    ImageView musicCircle;
    private ViewPagerAdapter mViewPagerAdapter;
    private ObjectAnimator mNeedleAnimator;
    private ViewPager viewPager;

//    private MusicLitener musicLitener;


    public DiscView(Context context) {
        super(context);
    }

    public DiscView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DiscView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
