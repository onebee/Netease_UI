package com.one.netease.ui.colorFilter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.one.netease.ui.R;

import androidx.annotation.Nullable;

/**
 * @author diaokaibin@gmail.com on 2020-01-11.
 */
public class ColorFilterView extends View {
    private Paint mPaint;
    private Bitmap bitmap;
    private ColorMatrixColorFilter mMatrixColorFilter;

    {

        mPaint = new Paint();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.girl);
    }

    public ColorFilterView(Context context) {
        super(context);

    }

    public ColorFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * R' = R * mul.R / 0xff + add.R
         * G' = G * mul.G / 0xff + add.G
         * B' = B * mul.B / 0xff + add.B
         */
        //红色去除掉
//        LightingColorFilter lightingColorFilter = new LightingColorFilter(0x00ffff, 0x000000);
//        mPaint.setColorFilter(lightingColorFilter);
//        canvas.drawBitmap(bitmap,0,0,mPaint);

        // 原始图片
//        LightingColorFilter lightingColorFilter = new LightingColorFilter(0xffffff, 0x000000);
//        mPaint.setColorFilter(lightingColorFilter);
//        canvas.drawBitmap(bitmap,0,0,mPaint);

        // 绿色更亮
//        LightingColorFilter lightingColorFilter = new LightingColorFilter(0xffffff, 0x900000);
//        mPaint.setColorFilter(lightingColorFilter);
//        canvas.drawBitmap(bitmap,0,0,mPaint);

//        PorterDuffColorFilter duffColorFilter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN);
//        mPaint.setColorFilter(duffColorFilter);
//        canvas.drawBitmap(bitmap,0,0,mPaint);

        float[] colorMatrix = {
                1,0,0,0,0,   //red
                0,1,0,0,0,   //green
                0,0,2,0,0,   //blue
                0,0,0,1,0    //alpha
        };

        // 浪漫
        final float colormatrix_langman[] = {
                0.9f, 0, 0, 0, 63.0f,
                0, 0.9f, 0, 0, 63.0f,
                0, 0, 0.9f, 0, 63.0f,
                0, 0, 0, 1.0f, 0};

        ColorMatrix cm = new ColorMatrix();

        //色调调节
        cm.setRotate(0, 45);

        mMatrixColorFilter = new ColorMatrixColorFilter(colormatrix_langman);
        mPaint.setColorFilter(mMatrixColorFilter);
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
    }
}
