package com.example.customview.ui.opengl.renderer;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import com.example.customview.R;
import com.example.customview.ui.opengl.util.MatrixHelper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GL1Render implements GLSurfaceView.Renderer {

//    private float[] tableVerticesWithTriangles = {
//            0f, 0f, 0f, 0f, 1f, 1f, 0f,
//            -0.5f, -0.9f, 0f, 0f, 0.7f, 0.7f, 0.7f,
//            0.5f, -0.9f, 0f, 0f, 0.7f, 0.7f, 0.7f,
//            0.5f, 0.9f, 0f, 0f, 0.7f, 0.7f, 0.7f,
//            -0.5f, 0.9f, 0f, 0f, 0.7f, 0.7f, 0.7f,
//            -0.5f, -0.9f, 0f, 0f, 0.7f, 0.7f, 0.7f,
//
//            -0.5f, 0f, 0f, 0f, 1f, 0f, 0f,
//            0.5f, 0f, 0f, 0f, 1f, 0f, 0f,
//
//            0f, 0.45f, 0f, 0f, 1f, 0f, 0f,
//            0f, -0.45f, 0f, 0f, 0f, 0f, 1f,
//
//    };

    private float[] tableVerticesWithTriangles = {
            0f, 0f, 1f, 1f, 0f,
            -0.5f, -0.9f, 0.7f, 0.7f, 0.7f,
            0.5f, -0.9f, 0.7f, 0.7f, 0.7f,
            0.5f, 0.9f, 0.7f, 0.7f, 0.7f,
            -0.5f, 0.9f, 0.7f, 0.7f, 0.7f,
            -0.5f, -0.9f, 0.7f, 0.7f, 0.7f,

            -0.5f, 0f, 1f, 0f, 0f,
            0.5f, 0f, 1f, 0f, 0f,

            0f, 0.45f, 1f, 0f, 0f,
            0f, -0.45f, 0f, 0f, 1f,

    };


    private static final int BYTES_PER_FLOAT = 4;
    private final FloatBuffer vertexData;
    private Context context;

    private int vertexShader;
    private int fragmentShader;
    private int progreamId;

    ////////////////////////////////////////////////////
    private static final int POSITION_COMPONENT_COUNT = 2;
    private static final int COLOR_COMPONENT_COUNT = 3;
    private static final int STRIDE =
            (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT) * BYTES_PER_FLOAT;

    private int vColorLocation, aPositionLocation, aColorLocation, uMatrixLocation;
    private final String A_Color = "a_Color";
    private final String V_Color = "v_Color";
    private final String A_Position = "a_Position";
    private final String U_Matrix = "u_Matrix";

    private final float[] projectionMatrix = new float[16];

    private final float[] modelMatrix = new float[16];

    public GL1Render(Context context) {
        this.context = context;
        //本地内存开辟区间
        vertexData = ByteBuffer.allocateDirect(tableVerticesWithTriangles.length * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexData.put(tableVerticesWithTriangles);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        vertexShader = complieVertexShader(readTextFileFromResource(context, R.raw.simple_vertex_shader));
        fragmentShader = complieFragmentShader(readTextFileFromResource(context, R.raw.simple_fragment_shader));
        progreamId = linkProgram(vertexShader, fragmentShader);
        GLES20.glUseProgram(progreamId);

        vColorLocation = GLES20.glGetUniformLocation(progreamId, V_Color);
        aPositionLocation = GLES20.glGetAttribLocation(progreamId, A_Position);
        aColorLocation = GLES20.glGetAttribLocation(progreamId, A_Color);
        uMatrixLocation = GLES20.glGetUniformLocation(progreamId, U_Matrix);

        vertexData.position(0);
        GLES20.glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GLES20.GL_FLOAT, false, STRIDE, vertexData);
        GLES20.glEnableVertexAttribArray(aPositionLocation);
        vertexData.position(POSITION_COMPONENT_COUNT);
        GLES20.glVertexAttribPointer(aColorLocation, COLOR_COMPONENT_COUNT, GLES20.GL_FLOAT, false, STRIDE, vertexData);
        GLES20.glEnableVertexAttribArray(aColorLocation);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
//        final float aspectRatio = width > height ? (float) width / (float) height : (float) height / (float) width;
//        Log.d("ftd", "aspectRatio:" + aspectRatio);

//        if (width > height) {
//            Matrix.orthoM(projectionMatrix, 0, -aspectRatio, aspectRatio, -1f, 1f, -1f, 1f);
//        } else {
//            Matrix.orthoM(projectionMatrix, 0, -1f, 1f, -aspectRatio, aspectRatio, -1f, 1 f);
//        }

        MatrixHelper.perspectiveM(projectionMatrix, 45, (float) width / (float) height, 1f, 10f);
        Matrix.setIdentityM(modelMatrix, 0);//创建单位矩阵
        Matrix.translateM(modelMatrix, 0, 0f, 0f, -2.5f);//矩阵Z 轴移动-3f距离
        Matrix.rotateM(modelMatrix, 0, -60f, 1f, 0f, 0);

        final float[] temp = new float[16];
        Matrix.multiplyMM(temp, 0, projectionMatrix, 0, modelMatrix, 0);
        System.arraycopy(temp, 0, projectionMatrix, 0, temp.length);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, projectionMatrix, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 6);
        GLES20.glDrawArrays(GLES20.GL_LINES, 6, 2);
        GLES20.glDrawArrays(GLES20.GL_POINTS, 8, 1);
        GLES20.glDrawArrays(GLES20.GL_POINTS, 9, 1);
    }

    public static String readTextFileFromResource(Context context, int resourceId) {
        StringBuilder body = new StringBuilder();
        try {
            InputStream inputStream =
                    context.getResources().openRawResource(resourceId);
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String nextLine;
            while ((nextLine = bufferedReader.readLine()) != null) {
                body.append(nextLine);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return body.toString();
    }

    public static int complieFragmentShader(String shaderCode) {
        return complieShader(GLES20.GL_FRAGMENT_SHADER, shaderCode);
    }

    public static int complieVertexShader(String shaderCode) {
        return complieShader(GLES20.GL_VERTEX_SHADER, shaderCode);
    }

    private static int complieShader(int type, String shaderCode) {
        final int shaderObjectId = GLES20.glCreateShader(type);
        if (shaderObjectId == 0) {
            Log.e("ftd", "createShader error");
            return 0;
        }
        GLES20.glShaderSource(shaderObjectId, shaderCode);
        GLES20.glCompileShader(shaderObjectId);
        final int[] compileStatus = new int[1];
        GLES20.glGetShaderiv(shaderObjectId, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
        if (compileStatus[0] == 0) {
            Log.e("ftd", "createShader failed");
            Log.e("ftd", "glGetShaderInfoLog: " + GLES20.glGetShaderInfoLog(shaderObjectId));
            GLES20.glDeleteShader(shaderObjectId);
            return 0;
        }


        return shaderObjectId;
    }

    public static int linkProgram(int vertexShaderId, int fragmentShaderId) {
        final int programId = GLES20.glCreateProgram();
        if (programId == 0) {
            Log.e("ftd", "create program error");
            return 0;
        }

        GLES20.glAttachShader(programId, vertexShaderId);
        GLES20.glAttachShader(programId, fragmentShaderId);
        GLES20.glLinkProgram(programId);

        final int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(programId, GLES20.GL_LINK_STATUS, linkStatus, 0);
        if (linkStatus[0] == 0) {
            GLES20.glDeleteProgram(programId);
            return 0;
        }

        return programId;
    }


}
