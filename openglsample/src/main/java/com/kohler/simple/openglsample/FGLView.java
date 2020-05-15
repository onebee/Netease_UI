package com.kohler.simple.openglsample;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * @author diaokaibin@gmail.com on 2020/5/14.
 *
 */
public class FGLView extends GLSurfaceView {
    public FGLView(Context context) {
        super(context);
    }

    public FGLView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setEGLContextClientVersion(2);
        setRenderer(new FGLRender(this));




        // 效率高 自己调用高
//        requestRender();

        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);



    }
}
