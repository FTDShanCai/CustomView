package com.example.customview.ui.opengl.util;

import android.content.res.Resources;
import android.opengl.GLES30;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ShaderUtil {
    private static final String TAG = "OPENGL";

    /**
     * 加载指定着色器
     *
     * @param shaderType
     * @param suorce
     * @return
     */
    public static int loadShader(int shaderType, String suorce) {
        int shader = GLES30.glCreateShader(shaderType);
        if (shader != 0) {
            GLES30.glShaderSource(shader, suorce);
            GLES30.glCompileShader(shader);
            int[] compiled = new int[1];
            GLES30.glGetShaderiv(shader, GLES30.GL_COMPILE_STATUS, compiled, 0);
            if (compiled[0] == 0) {
                Log.e(TAG, "could not compile shader " + shaderType + ":");
                Log.e(TAG, GLES30.glGetShaderInfoLog(shader));
                GLES30.glDeleteShader(shader);
                shader = 0;
            }
        }
        return shader;
    }

    /**
     * 创建指定着色器
     *
     * @param vertexSource
     * @param framentSource
     * @return
     */
    public static int createProgram(String vertexSource, String framentSource) {
        //加载顶点着色器
        int vertexShador = loadShader(GLES30.GL_VERTEX_SHADER, vertexSource);
        if (vertexShador == 0) return 0;
        //加载片元着色器
        int pixelShador = loadShader(GLES30.GL_FRAGMENT_SHADER, framentSource);
        if (pixelShador == 0) return 0;
        int program = GLES30.glCreateProgram();
        if (program != 0) {
            GLES30.glAttachShader(program, vertexShador);//向程序加入着色器
            checkGlError("glAttachShader");
            GLES30.glAttachShader(program, pixelShador);
            checkGlError("glAttachShader");
            GLES30.glLinkProgram(program);
            int[] linkStatus = new int[1];
            GLES30.glGetProgramiv(program, GLES30.GL_LINK_STATUS, linkStatus, 0);
            if (linkStatus[0] != GLES30.GL_TRUE) {
                Log.e(TAG, "could not link program:");
                Log.e(TAG, GLES30.glGetProgramInfoLog(program));
                GLES30.glDeleteProgram(program);
                program = 0;
            }
        }
        return program;
    }

    /**
     * 检查每一步操作是否有误
     *
     * @param op
     */
    public static void checkGlError(String op) {
        int error;
        while ((error = GLES30.glGetError()) != GLES30.GL_NO_ERROR) {
            Log.e(TAG, op + ":glError " + error);
            throw new RuntimeException(op + ":glError " + error);
        }
    }

    public static String loadFromAssetsFile(String fname, Resources r) {
        String result = null;
        try {
            InputStream in = r.getAssets().open(fname);
            int ch = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((ch = in.read()) != -1) {
                baos.write(ch);
            }

            byte[] buff = baos.toByteArray();
            baos.close();
            in.close();
            result = new String(buff, "UTF-8");
            result = result.replaceAll("\\r\\n", "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
