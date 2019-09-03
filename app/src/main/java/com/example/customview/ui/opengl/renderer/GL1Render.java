package com.example.customview.ui.opengl.renderer;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.example.customview.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GL1Render implements GLSurfaceView.Renderer {

    private float[] tableVerticesWithTriangles = {
            0f, 0f,
            9f, 14f,
            0f, 14f,

            0f, 0f,
            9f, 0f,
            9f, 14f,

            0f, 7f,
            9f, 7f,

            4.5f, 2f,

            4.5f, 12f

    };

    private static final int BYTES_PER_FLOAT = 4;
    private final FloatBuffer vertexData;
    private Context context;

    private int vertexShader;
    private int fragmentShader;

    private int progreamId;

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
//        GLES20.glClearColor(1.0f, 1.0f, 0.0f, 0.0f);
        vertexShader = complieVertexShader(readTextFileFromResource(context, R.raw.simple_vertex_shader));
        fragmentShader = complieFragmentShader(readTextFileFromResource(context, R.raw.simple_fragment_shader));
        progreamId = linkProgram(vertexShader, fragmentShader);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
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
                body.append("/n");
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
        final int[] compileStatus = new int[1];
        GLES20.glGetShaderiv(shaderObjectId, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
        if (compileStatus[0] == 0) {
            GLES20.glDeleteShader(shaderObjectId);
            Log.e("ftd", "createShader failed");
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
