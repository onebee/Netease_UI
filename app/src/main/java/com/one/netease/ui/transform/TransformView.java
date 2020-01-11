package com.one.netease.ui.transform;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author diaokaibin@gmail.com on 2020-01-11.
 * <p>
 * 变换操作
 */
public class TransformView extends View {


    private Paint mPaint;


    {

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    public TransformView(Context context) {
        super(context);
    }

    public TransformView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TransformView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 1. 平移操作
//        canvas.drawRect(0, 0, 400, 400, mPaint);
//        canvas.translate(50, 50);
//
//        mPaint.setColor(Color.BLUE);
//
//        canvas.drawRect(0, 0, 400, 400, mPaint);
//        canvas.drawLine(0,0,600,600,mPaint);


        // 缩放操作
//        canvas.drawRect(200,200,700,700,mPaint);
//        canvas.scale(0.5f,0.5f);

        /**
         * 1. translate(px,py)
         * 2. scale(sx,sy)
         * 3. 再反向translate(-px,-py)
         */
//        canvas.scale(0.5f,0.5f,200,200);
//        mPaint.setColor(Color.BLUE);
//
//        canvas.drawRect(200,200,700,700,mPaint);



        // 旋转操作
//        canvas.translate(50,50);
//        canvas.drawRect(0,0,700,700,mPaint);
//        canvas.rotate(45);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(0,0,700,700,mPaint);
//
//        canvas.drawRect(400,400,900,900,mPaint);
//        canvas.rotate(45,650,650);
//        mPaint.setColor(Color.GREEN);
//        canvas.drawRect(400,400,900,900,mPaint);

        //倾斜操作
//        canvas.drawRect(0,0,400,400,mPaint);
////        canvas.skew(1,0);// 在x 方向倾斜45度. Y 轴逆时针旋转45
//        canvas.skew(0,1);// 在y 方向倾斜45度. X 轴顺时针旋转45
//        mPaint.setColor(Color.GREEN);
//        canvas.drawRect(0,0,400,400,mPaint);

        //切割
//        canvas.drawRect(200,200,700,700,mPaint);
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(200,800,700,1300,mPaint);
//        canvas.clipRect(200,200,700,700);// 画布被裁剪
//        canvas.drawCircle(100,100,100,mPaint);// 坐标超出裁剪区域,无法被绘制
//        canvas.drawCircle(300,300,100,mPaint);


//        canvas.drawRect(200,200,700,700,mPaint);
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(200,800,700,1300,mPaint);
//        canvas.clipOutRect(200,200,700,700);// 画布被裁剪
//        canvas.drawCircle(100,100,100,mPaint);// 坐标超出裁剪区域,无法被绘制
//        canvas.drawCircle(300,300,100,mPaint);


        //矩阵

        canvas.drawRect(0, 0, 700, 700, mPaint);
        mPaint.setColor(Color.BLUE);
        Matrix matrix = new Matrix();
//        matrix.setTranslate(50,50);
//        matrix.setRotate(45);
        matrix.setScale(0.5f,0.5f);
        canvas.setMatrix(matrix);
        canvas.drawRect(0,0,700,700,mPaint);
    }
}
