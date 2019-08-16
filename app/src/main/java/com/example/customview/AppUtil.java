package com.example.customview;

import android.content.Context;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class AppUtil {


    public static int dp2px(Context context, int i) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (i * scale + 0.5f);
    }

    public static float getStatusHeight(Context context) {
        return dp2px(context, 24);
    }
}
