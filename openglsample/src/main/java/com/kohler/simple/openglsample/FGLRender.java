package com.kohler.simple.openglsample;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.view.View;

import com.kohler.simple.openglsample.shape.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @author diaokaibin@gmail.com on 2020/5/14.
 */
public class FGLRender implements GLSurfaceView.Renderer {

    protected View mView;
    Triangle mTriangle;

    public FGLRender(View view) {
        mView = view;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        GLES20.glClearColor(0, 0, 0, 0);

        mTriangle = new Triangle();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    /**
     * 不断被调用 inviladate
     */
    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        mTriangle.onDrawFrame(gl);
    }
}
