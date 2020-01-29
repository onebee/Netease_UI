package com.one.netease.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author diaokaibin@gmail.com on 2020-01-29.
 */
public class CarView extends View {

    private Bitmap carBitmap;
    private Path path;
    private PathMeasure pathMeasure;//路径计算

    private float distanceRatio =0;
    private Paint circlePaint;
    private Paint carPaint;

    private Matrix carMatrix;  // 针对car bitmap 图片操作的矩阵



    public CarView(Context context) {
        super(context);
    }

    public CarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
