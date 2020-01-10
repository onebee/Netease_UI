package com.one.netease.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author diaokaibin@gmail.com on 2020-01-10.
 * <p>
 * Paint 高级应用渲染
 */
public class GradientLayout extends View {
    private Paint mPaint,mPaint2;
    private Shader mShader;
    private Bitmap mBitmap;

    public GradientLayout(Context context) {
        this(context, null);
    }

    public GradientLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradientLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint2.setStyle(Paint.Style.STROKE);
        mPaint2.setColor(Color.BLUE);
//        mPaint.setColor(Color.BLUE);
//        mPaint.setAlpha(255);
        mPaint.setAntiAlias(true);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        mShader = new LinearGradient(0, 0, 500, 500, new int[]{Color.BLUE, Color.RED}, new float[]{0f,1}, Shader.TileMode.CLAMP);
//        mPaint.setShader(mShader);
//        canvas.drawCircle(250, 250, 250, mPaint);

//        mShader = new RadialGradient(250, 250, 50, new int[]{Color.RED, Color.YELLOW}, new float[]{0,1}, Shader.TileMode.REPEAT);
//        mPaint.setShader(mShader);
//        canvas.drawCircle(250,250,250,mPaint);
//
//        canvas.drawCircle(250,250,50,mPaint2);


//        mShader = new SweepGradient(250, 250, new int[]{Color.RED, Color.YELLOW}, new float[]{0.5f, 1});
//        mPaint.setShader(mShader);
//        canvas.drawCircle(250,250,250,mPaint);


        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.beauty);
//        mShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
//        mPaint.setShader(mShader);
//
//        canvas.drawRect(0,0,500,500,mPaint);


        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        LinearGradient linearGradient = new LinearGradient(0, 0, 1000, 1600, new int[]{Color.RED, Color.GREEN, Color.BLUE}, null, Shader.TileMode.CLAMP);
        mShader = new ComposeShader(bitmapShader, linearGradient, PorterDuff.Mode.MULTIPLY);
        mPaint.setShader(mShader);
        canvas.drawCircle(250, 250, 250, mPaint);
    }
}
