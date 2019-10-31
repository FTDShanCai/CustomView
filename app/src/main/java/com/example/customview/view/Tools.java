package com.example.customview.view;

import android.content.Context;

public class Tools {


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, double dpValue) {
        int res = 0;
        final float scale = context.getResources().getDisplayMetrics().density;
        if (dpValue < 0)
            res = -(int) (-dpValue * scale + 0.5f);
        else
            res = (int) (dpValue * scale + 0.5f);
        return res;
    }
}
