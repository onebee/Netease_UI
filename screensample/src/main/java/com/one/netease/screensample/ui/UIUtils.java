package com.one.netease.screensample.ui;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * @author diaokaibin@gmail.com on 2020-01-23.
 */
public class UIUtils {


    private static UIUtils instance;

    // 标准的
    public static final float STANDARD_WIDTH = 1080f;
    public static final float STANDARD_HEIGHT = 1920f;

    //实际设备信息
    public static float displayMetriceWidth;
    public static float displayMetriceHeight;

    public static float systemBarHeight;


    public static UIUtils getInstance(Context context) {
        if (instance == null) {
            instance = new UIUtils(context);
        }
        return instance;
    }

    public static UIUtils getInstance() {
        if (instance == null) {
            throw new RuntimeException("UIUtil 应该先调用含有构造方法进行初始化");
        }
        return instance;
    }


    public UIUtils(Context context) {
        // 计算缩放系数
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (displayMetriceWidth == 0.0f || displayMetriceHeight == 0.0f) {

            // 在这里得到设备的真实值
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);

            systemBarHeight = getSystemBarHeight(context);
            // 横屏
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {

                this.displayMetriceWidth = (float) displayMetrics.heightPixels;
                this.displayMetriceHeight = (float) displayMetrics.widthPixels - systemBarHeight;

            } else {

                // 竖屏
                this.displayMetriceWidth = (float) displayMetrics.widthPixels;
                this.displayMetriceHeight = (float) (displayMetrics.heightPixels - systemBarHeight);

            }

        }

    }

    /**
     * 横向的缩放系数
     * @return
     */
    public static float getHorizontalScaleValue() {
        return (float)(displayMetriceWidth)/STANDARD_WIDTH;
    }

    /**
     * 纵向的缩放系数
     * @return
     */
    public static float getVerticalScaleValue() {
        return (float)(displayMetriceHeight)/(STANDARD_HEIGHT-systemBarHeight);
    }


    private int getSystemBarHeight(Context context) {
        return getValue(context, "com.android.internal.R$dimen", "system_bar_height", 48);
    }


    //com.android.internal.R$dimen   system_bar_height
    private int getValue(Context context, String dimeClass, String system_bar_height, int defaultValue) {
        try {
            Class<?> clazz = Class.forName(dimeClass);
            Object object = clazz.newInstance();
            Field field = clazz.getField(system_bar_height);
            int id = Integer.parseInt(field.get(object).toString());
            return context.getResources().getDimensionPixelSize(id);

        } catch (Exception e) {
        }
        return defaultValue;
    }

    public int getWidth(int width) {
        return Math.round((float)width * this.displayMetriceWidth / STANDARD_WIDTH);
    }
    public int getHeight(int height) {
        return Math.round((float)height * this.displayMetriceHeight / (STANDARD_HEIGHT-systemBarHeight));
    }
}
