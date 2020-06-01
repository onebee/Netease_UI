package com.one.immersion;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @author diaokaibin@gmail.com on 2020/5/15.
 */
public class Ima extends androidx.appcompat.widget.AppCompatImageView {


    Paint mPaint = new Paint();
    {

        mPaint.setColor(Color.LTGRAY);
        mPaint.setStrokeWidth(5);
        mPaint.setTextSize(25);

    }

    public Ima(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();

        Log.i("ima", "  width = " + width + "\n" + "  height = " + height);

        canvas.drawText("hhhh",0,56,mPaint);

        canvas.drawLine(0,0,width,height,mPaint);


    }
}
