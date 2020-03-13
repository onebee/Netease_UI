package com.one.player.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;

import com.one.player.R;

/**
 * @author diaokaibin@gmail.com on 2020/3/13.
 */
public class BackgroundAnimationRelativeLayout extends RelativeLayout {
    private LayerDrawable mLayerDrawable;
    private ObjectAnimator mObjectAnimator;


    public BackgroundAnimationRelativeLayout(Context context) {
        this(context, null);
    }

    public BackgroundAnimationRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public BackgroundAnimationRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        Drawable backgroundDrawable = getContext().getDrawable(R.drawable.ic_blackground);
        Drawable[] drawables = new Drawable[2];
        //  初始化背景时先将前景颜色设为一致
        drawables[0] = backgroundDrawable;
        drawables[1] = backgroundDrawable;

        mLayerDrawable = new LayerDrawable(drawables);

        // 监听动画的执行
        mObjectAnimator = ObjectAnimator.ofFloat(this, "number", 0f, 1f);
        mObjectAnimator.setDuration(500);
        mObjectAnimator.setInterpolator(new AccelerateInterpolator());

        /**
         * 监听动画的进度
         */
        mObjectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//              animation 0 开始 1结束

                // animation 的动画进度是怎么得到的  (VSYC - start)/时间
                int foregroundAlpha = (int) ((float) animation.getAnimatedValue()*255);
                mLayerDrawable.getDrawable(1).setAlpha(foregroundAlpha);
                setBackground(mLayerDrawable);
            }
        });
        /**
         * 监听动画的状态
         */
        mObjectAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                /**
                 * 动画结束后,记得将原来的背景图及时更新
                 */
                mLayerDrawable.setDrawable(0,mLayerDrawable.getDrawable(1));
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationPause(Animator animation) {
                super.onAnimationPause(animation);
            }


        });
    }

    public void setForeground(Drawable drawable) {
        mLayerDrawable.setDrawable(1, drawable);
        mObjectAnimator.start();
    }
}
