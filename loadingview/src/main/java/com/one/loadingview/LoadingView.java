package com.one.loadingview;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
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
    private float downDistance=0; // 水平位置下降的距离
    private float maxDownDistance; // 水平位置下降的距离(最低点)


    private float upDistance=0;  // 从底部上弹的距离
    private float freeDownDistance=0; // 自由落体的距离
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
        initAttr(context, attrs);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(strokeWidth);
        path = new Path();
        holder = getHolder();
        // 给 SurfaceHolder 添加回调
        holder.addCallback(this);

    }


    private void initAttr(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingView);

        ballColor = typedArray.getColor(R.styleable.LoadingView_ball_color, Color.BLUE);
        lineColor = typedArray.getColor(R.styleable.LoadingView_line_color, Color.BLUE);
        lineWidth = typedArray.getDimensionPixelOffset(R.styleable.LoadingView_line_width, 200);
        strokeWidth = typedArray.getDimensionPixelOffset(R.styleable.LoadingView_stroke_width, 4);
        maxDownDistance = typedArray.getDimensionPixelOffset(R.styleable.LoadingView_max_down, 50);
        maxFreeDownDistance = typedArray.getColor(R.styleable.LoadingView_ball_color, 50);
        ballRadius = typedArray.getDimensionPixelOffset(R.styleable.LoadingView_ball_radius, 10);


        typedArray.recycle();

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        drawView();//


    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void run() {
        // 绘制动画 循环


    }

    private void drawView() {


        try {
            // 创建画布
            if (holder != null) {
                canvas = holder.lockCanvas();
//            canvas.drawColor(0, PorterDuff.Mode.CLEAR); // 清空屏幕, 解决屏幕残影
                canvas.drawColor(0xFFFFFFFF);
                paint.setColor(lineColor);
                path.reset();

                path.moveTo(getWidth() / 2f - lineWidth / 2f, getHeight() / 2f);
                if (mLoadingState == LoadingState.DOWN) {
                    // 小球在绳子上 下降
                    path.rQuadTo(lineWidth / 2f, 2 * downDistance, lineWidth, 0);
                    paint.setColor(lineColor);
                    paint.setStyle(Paint.Style.STROKE);
                    canvas.drawPath(path, paint);

                    // 绘制小球
                    paint.setColor(ballColor);
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawCircle(getWidth() / 2f,
                            getHeight() / 2f + downDistance - ballRadius - strokeWidth/2f,
                            ballRadius, paint
                    );
                } else {
                    // 上升或 自由落体
                }

                paint.setColor(ballColor);
                paint.setStyle(Paint.Style.FILL);


                canvas.drawCircle(getWidth() / 2f - lineWidth / 2f,
                        getHeight() / 2f, ballRadius, paint
                );

                canvas.drawCircle(getWidth() / 2f + lineWidth / 2f,
                        getHeight() / 2f, ballRadius, paint
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (canvas != null) {
                holder.unlockCanvasAndPost(canvas);
            }

        }




    }
}
