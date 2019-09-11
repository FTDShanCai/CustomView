package com.example.customview.ui.opengl.util;

import android.content.Context;
import android.opengl.GLES20;

public class TextureHelper {

    public static int loadTexture(Context context, int resourceId) {

        final int[] textureObjects = new int[1];
        GLES20.glGenTextures(1, textureObjects, 0);
        if (textureObjects[0] == 0) {
            return 0;
        }
        return textureObjects[0];
    }

}
