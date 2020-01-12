package com.one.netease.ui.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author diaokaibin@gmail.com on 2020-01-12.
 */
public class PathView extends View {

    private Paint mPaint;
    private Path mPath;


    {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);

    }

    public PathView(Context context) {
        super(context);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        mPaint.setStyle(Paint.Style.FILL);
        // 一阶贝塞尔曲线 表示的就是一条直线
//        mPath.moveTo(100,70);
////        mPath.lineTo(140,800);
//        // 等同于上面一行代码
//        mPath.rLineTo(40,730);
//        mPath.lineTo(250,600);
//        mPath.close();


        // 添加弧形
//        canvas.drawRect(199,199,401,401,mPaint);
//        mPaint.setColor(Color.BLUE);
//        mPath.addArc(200,200,400,400,-255,255);
//
//        // CW 顺时针绘制 CCW 逆时针绘制
//        mPath.addRect(500,500,900,900, Path.Direction.CW);
//
//
//        mPath.addCircle(700,700,200, Path.Direction.CW);
//
//        mPath.addOval(0,0,500,300, Path.Direction.CW);


        // 追加图形
//        mPath.arcTo(400,200,600,400,-180,225,false);

//        mPath.moveTo(0,0);
//        mPath.lineTo(100,100);
//        /**
//         * forceMoveTo true 绘制时移动起点
//         *              false 绘制时连接最后一个点 与圆弧起点
//         */
//        mPath.arcTo(400,200,600,400,0,270,false);


        // 添加一个路径
//        mPath.moveTo(100, 70);
//        mPath.lineTo(140, 180);
//        mPath.lineTo(250, 330);
//        mPath.lineTo(400, 630);
//        mPath.lineTo(100, 830);
//
//        Path newPath = new Path();
//        newPath.moveTo(100, 1000);
//        newPath.lineTo(600, 1300);
//        newPath.lineTo(400, 1700);
//        mPath.addPath(newPath);


//                    //添加圆角矩形， CW顺时针，CCW逆时针
//        RectF rectF5 = new RectF(200, 800, 700, 1200);
//        mPath.addRoundRect(rectF5, 20, 20, Path.Direction.CCW);


//        //画二阶贝塞尔曲线
//        mPath.moveTo(300, 500);
//        mPath.quadTo(500, 100, 800, 500);
        //参数表示相对位置，等同于上面一行代码
//        mPath.rQuadTo(200, -400, 500, 0);

        //画三阶贝塞尔曲线
        mPath.moveTo(300, 500);
        mPath.cubicTo(500, 100, 600, 1200, 800, 500);
        //参数表示相对位置，等同于上面一行代码
//        mPath.rCubicTo(200, -400, 300, 700, 500, 0);


        canvas.drawPath(mPath, mPaint);
    }
}
