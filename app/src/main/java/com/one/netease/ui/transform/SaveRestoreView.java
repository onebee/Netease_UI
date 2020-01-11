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
 */
public class SaveRestoreView extends View {
    Paint mPaint;

    {

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);

    }

    public SaveRestoreView(Context context) {
        super(context);
    }

    public SaveRestoreView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SaveRestoreView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Log.i(">>>","onDraw : " + canvas.getSaveCount());
//
//        canvas.drawRect(200, 200, 700, 700, mPaint);
//       int state = canvas.save();
//        Log.i(">>>","onDraw : " + canvas.getSaveCount());
//        canvas.translate(50, 50);
//
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(0, 0, 500, 500, mPaint);
//
//        canvas.save();
//        Log.i(">>>","onDraw : " + canvas.getSaveCount());
//
//        canvas.translate(50,50);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(0,0,500,500,mPaint);
//
////        canvas.restore();
//        Log.i(">>>","onDraw : " + canvas.getSaveCount());
//
////        canvas.restore();
//        canvas.restoreToCount(state);
//        Log.i(">>>","onDraw : " + canvas.getSaveCount());
//
//        canvas.drawLine(0, 0, 500, 500, mPaint);


        canvas.drawRect(200,200,700,700,mPaint);
        int layerId = canvas.saveLayer(0, 0,700, 700, mPaint);
        // 在此的操作保存在一个图层上面
        mPaint.setColor(Color.GRAY);
        Matrix matrix = new Matrix();
        matrix.setTranslate(100,100);
        canvas.setMatrix(matrix);
        canvas.drawRect(0,0,700,700,mPaint);
        canvas.restoreToCount(layerId);

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0,0,100,100,mPaint);



    }
}
