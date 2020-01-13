package com.one.netease.ui.path;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import com.one.netease.ui.R;

import androidx.annotation.Nullable;

/**
 * @author diaokaibin@gmail.com on 2020-01-13.
 */
public class PathMeasureView extends View {

    public static final String TAG = "PathMeasureView";

    private Paint mPaint = new Paint();
    private Paint mPaintDst = new Paint();
    private Paint mLinePaint = new Paint(); //坐标系
    private Bitmap mBitmap;

    {

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(4);

        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(Color.RED);
        mLinePaint.setStrokeWidth(6);

        mPaintDst.setColor(Color.BLUE);
        mPaintDst.setStyle(Paint.Style.STROKE);
        mPaintDst.setStrokeWidth(10);
        //缩小图片
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.arrow, options);

    }


    public PathMeasureView(Context context) {
        super(context);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, mLinePaint);
        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), mLinePaint);

        canvas.translate(getWidth() / 2, getHeight() / 2);

//        Path path = new Path();
//        path.lineTo(0, 200);
//        path.lineTo(200, 200);
//        path.lineTo(200, 0);
//
//        PathMeasure pathMeasure = new PathMeasure();
//        pathMeasure.setPath(path, false);
//        Log.i(TAG, "onDraw forceClose = false: " + pathMeasure.getLength());
//
//        PathMeasure pathMeasure2 = new PathMeasure();
//        pathMeasure2.setPath(path, true);
//
//        Log.i(TAG, "onDraw forceClose = true: " + pathMeasure2.getLength());
//
//        PathMeasure pathMeasure1 = new PathMeasure(path, false);
//        Log.i(TAG, "onDraw forceClose = false : " + pathMeasure1.getLength());
//
//
//        path.lineTo(200, -200);
//        Log.i(TAG, "onDraw forceClose = false : " + pathMeasure1.getLength());
//        pathMeasure1.setPath(path, false);
//        Log.i(TAG, "onDraw forceClose = false : " + pathMeasure1.getLength());


//        Path path = new Path();
//        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
//
////        path.addCircle(0,0,200, Path.Direction.CW);
//        Path dst = new Path();
//        dst.lineTo(-300,-300);
//
//        PathMeasure pathMeasure = new PathMeasure(path, false);
//
//        /**
//         * startD : 开始截取的位置
//         * stopD:  结束截取的位置
//         * startWithMoveTo : true --> 保持截取到的path 第一个点的位置不变
//         */
//        pathMeasure.getSegment(200, 1000, dst, true);
//
//        canvas.drawPath(path, mPaint);
//
//        canvas.drawPath(dst, mPaintDst);


//        Path path = new Path();
//
//        path.addRect(-100, -100, 100, 100, Path.Direction.CW);
//        path.addOval(-200, -200, 200, 200, Path.Direction.CW);
//        path.addRect(-400, -400, 400, 400, Path.Direction.CW);
//
//        canvas.drawPath(path, mPaint);
//
//        PathMeasure pathMeasure = new PathMeasure(path, false);
//        Log.i(TAG, "onDraw  1--: " + pathMeasure.getLength());
//        pathMeasure.nextContour();
//        Log.i(TAG, "onDraw  2--: " + pathMeasure.getLength());
//        pathMeasure.nextContour();
//        Log.i(TAG, "onDraw  3--: " + pathMeasure.getLength());


//        mPath.addCircle(0, 0, 200, Path.Direction.CW);
        mPath.reset();
        mPath.addCircle(0, 0, 200, Path.Direction.CW);
        canvas.drawPath(mPath, mPaint);

        mFloat += 0.01;
        if (mFloat >= 1) {
            mFloat = 0;
        }

//        PathMeasure pathMeasure = new PathMeasure(mPath, false);
//        pathMeasure.getPosTan(pathMeasure.getLength()*mFloat, pos, tan);
//        Log.i(TAG, " pos[0]=" + pos[0] + " --- pos[1]=" + pos[1]);
//        Log.i(TAG, " tan[0]=" + tan[0] + " --- tan[1]=" + tan[1]);
//
//        // 计算出当前的切线与X轴 夹角的度数
//        double degrees = Math.atan2(tan[1],tan[0]) * 180.0 /Math.PI;
//        Log.i(TAG, " degrees=" +degrees);
//
//        mMatrix.reset();
//        // 进行角度旋转
//        mMatrix.postRotate((float) degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
//        // 将图片的绘制中心与当前点重合
//        mMatrix.postTranslate(pos[0]-mBitmap.getWidth()/2,pos[1]-mBitmap.getHeight()/2);
//        canvas.drawBitmap(mBitmap,mMatrix,mPaint);

        PathMeasure pathMeasure = new PathMeasure(mPath, false);

        // 将位置信息和tan 信息保存在mMatrix 里面
        pathMeasure.getMatrix(pathMeasure.getLength() * mFloat, mMatrix,
                PathMeasure.POSITION_MATRIX_FLAG | PathMeasure.TANGENT_MATRIX_FLAG
        );

        // 将图片的旋转坐标调整到图片中心位置
        mMatrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);

        canvas.drawBitmap(mBitmap,mMatrix,mPaint);
        invalidate();

    }

    private float[] pos = new float[2];
    private float[] tan = new float[2];
    private Path mPath = new Path();
    private Matrix mMatrix = new Matrix();
    private float mFloat;
}
