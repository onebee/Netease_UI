package com.kohler.simple.openglsample.shape;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * @author diaokaibin@gmail.com on 2020/5/14.
 */
public class Triangle {

    int mProgram;
    private FloatBuffer vertexBuffer;

    float color[] = {1.0f, 1.0f, 1.0f, 1.0f};

    static float triangleCords[] =
            {

                    0.5f, 0.5f, 0.0f,
                    -0.5f, -0.5f, 0.0f,
                    0.5f, -0.5f, 0.0f,

            };


    //     native 函数 写好了
    private String vertextShaderCode = "attribute vec4 vPosition;" +
            "\n" +
            "void main(){" +
            "gl_Position=vPosition;" +
            "}";
    private final String fragmentShaderCode = "precision mediump float;\n" +
            "uniform  vec4 vColor;\n" +
            "void main(){\n" +
            "gl_FragColor=vColor;\t\n" +
            "}";


    public Triangle() {


        // GPU 里面的内存
        ByteBuffer bb = ByteBuffer.allocateDirect(triangleCords.length * 4);

        bb.order(ByteOrder.nativeOrder());

        vertexBuffer = bb.asFloatBuffer();

        // 把这门语法 推送给 GPU
        vertexBuffer.put(triangleCords);

        vertexBuffer.position(0);


        // 创建顶点着色器 并且在GPU 进行编译
        int shader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        GLES20.glShaderSource(shader, vertextShaderCode);
        GLES20.glCompileShader(shader);

        // 创建片元着色器 并且在GPU 进行编译
        int fragmentShader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        GLES20.glShaderSource(fragmentShader, fragmentShaderCode);
        GLES20.glCompileShader(fragmentShader);


        // 将片元着色器 和顶点着色器 放到统一程序 管理器
        mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(mProgram, shader);
        GLES20.glAttachShader(mProgram, fragmentShader);

        // 连接到着色器程序
        GLES20.glLinkProgram(mProgram);
    }

    /***
     * 1 初始化
     * 2 渲染
     *
     *
     *
     */


    public void onDrawFrame(GL10 gl) {

        // 渲染
        GLES20.glUseProgram(mProgram);

        // 指针 native 指针 gpu 的某个内存空间 vPosition
        int mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");


        // 打开 允许对变量读写
        GLES20.glDisableVertexAttribArray(mPositionHandle);

        GLES20.glVertexAttribPointer(mPositionHandle, 3, GLES20.GL_FLOAT, false,
                3 * 4, vertexBuffer
        );


        int mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");


        GLES20.glUniform4fv(mColorHandle, 1, color, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN,0,3);
        GLES20.glDisableVertexAttribArray(mPositionHandle);

    }
}
