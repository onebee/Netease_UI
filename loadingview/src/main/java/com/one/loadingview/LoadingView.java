package com.one.loadingview;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @author diaokaibin@gmail.com on 2020/8/2.
 */
public class LoadingView extends SurfaceView implements SurfaceHolder.Callback, Runnable {


    private enum LoadingState {
        DOWN, UP, FREE
    }

    private LoadingState mLoadingState = LoadingState.DOWN;


    private int ballColor;
    private int ballRadius;
    private int lineColor;
    private int lineWidth;
    private int strokeWidth;  // 绘制线宽
    private float downDistance; // 水平位置下降的距离
    private float maxDownDistance; // 水平位置下降的距离(最低点)


    private float upDistance;  // 从底部上弹的距离
    private float freeDownDistance; // 自由落体的距离
    private float maxFreeDownDistance; // 自由落体的距离(最高点)

    private ValueAnimator downControl;
    private ValueAnimator upControl;
    private ValueAnimator freeControl;


    private AnimatorSet animatorSet;

    private boolean isAnimationShowing;
    private SurfaceHolder holder;

    private Canvas canvas;
    private Paint paint;
    private Path path;
    private boolean isRunning; // 标志新线程是否在运行


    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int def) {
        super(context, attrs, def);
    }




    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void run() {

    }
}
