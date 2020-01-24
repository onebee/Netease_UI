package com.one.netease.screenadapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @author diaokaibin@gmail.com on 2020-01-24.
 */
public class Utils {


    // 屏幕显示的宽高
    private int mDisplayWidth;
    private int mDisplayHeight;



    // 设计稿参考的宽高
    public static final float STANDRAD_WIDTH = 720;
    public static final float STANDRAD_HEIGHT = 1280;



    private static Utils utils;

    private Utils(Context context) {

        // 获取屏幕宽和高
        if (mDisplayHeight == 0 || mDisplayWidth == 0) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (windowManager != null) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);

                if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                    //横屏
                    mDisplayWidth = displayMetrics.heightPixels;
                    mDisplayHeight = displayMetrics.widthPixels;
                } else {
                    mDisplayHeight = displayMetrics.heightPixels-getStatusBarHeight(context);
                    mDisplayWidth = displayMetrics.widthPixels;
                }

            }
        }


    }

    /**
     * 获取状态栏的高度
     * @param context
     * @return
     */
    public int getStatusBarHeight(Context context) {
        int resID = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resID > 0) {
            return context.getResources().getDimensionPixelSize(resID);
        }
        return 0;
    }

    public static Utils getInstance(Context context) {
        if (utils == null) {
            utils = new Utils(context.getApplicationContext());
        }
        return utils;
    }

    /**
     * 获取水平方向的缩放比例
     * @return
     */
    public float getHorizontalScale() {
        return mDisplayWidth/STANDRAD_WIDTH;
    }



    public float getVerticalSacle() {
        return mDisplayHeight/STANDRAD_HEIGHT;
    }

}
