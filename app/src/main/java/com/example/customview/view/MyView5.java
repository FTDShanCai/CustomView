package com.example.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.customview.R;

import androidx.annotation.Nullable;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class MyView5 extends View {
    private Bitmap mBitmap;             // 要绘制的图片
    private Matrix mPolyMatrix;// 测试setPolyToPoly用的Matrix
    private ColorMatrix colorMatrix;// 过滤颜色的矩阵

    public MyView5(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initBitmapAndMatrix();
    }

    public MyView5(Context context) {
        super(context);

        initBitmapAndMatrix();
    }

    private void initBitmapAndMatrix() {
        mBitmap = BitmapFactory.decodeResource(getResources(),
                R.mipmap.photo);

        mPolyMatrix = new Matrix();

        float[] src = {0, 0,                                    // 左上
                mBitmap.getWidth(), 0,                          // 右上
                mBitmap.getWidth(), mBitmap.getHeight(),        // 右下
                0, mBitmap.getHeight()};                        // 左下

        float[] dst = {0, 0,                                    // 左上
                mBitmap.getWidth(), 400,                        // 右上
                mBitmap.getWidth(), mBitmap.getHeight() - 200,  // 右下
                0, mBitmap.getHeight()};                        // 左下

        colorMatrix = new ColorMatrix(new float[]{
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0
        });
        // 核心要点
//        mPolyMatrix.setPolyToPoly(src, 0, dst, 0, src.length >> 1); // src.length >> 1 为位移运算 相当于处以2
        // 此处为了更好的显示对图片进行了等比缩放和平移(图片本身有点大)
        mPolyMatrix.postScale(0.8f, 0.8f);
//        mPolyMatrix.postTranslate(50, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        // 根据Matrix绘制一个变换后的图片
        canvas.drawBitmap(mBitmap, mPolyMatrix, paint);
    }


    public void 复古() {
        colorMatrix = new ColorMatrix(new float[]{
                0.393f, 0.769f, 0.189f, 0, 0,
                0.349f, 0.686f, 0.168f, 0, 0,
                0.272f, 0.534f, 0.131f, 0, 0,
                0, 0, 0, 1, 0
        });
        invalidate();
    }


    public void 黑白() {
        colorMatrix = new ColorMatrix(new float[]{
                0.33f, 0.59f, 0.11f, 0, 0,
                0.33f, 0.59f, 0.11f, 0, 0,
                0.33f, 0.59f, 0.11f, 0, 0,
                0, 0, 0, 1, 0
        });
        invalidate();
    }

    public void 曝光() {
        colorMatrix = new ColorMatrix(new float[]{
                -1, 0, 0, 1, 1,
                0, -1, 0, 1, 1,
                0, 0, -1, 1, 1,
                0, 0, 0, 1, 0
        });
        invalidate();
    }

    public void 原图() {
        colorMatrix = new ColorMatrix(new float[]{
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0
        });
        invalidate();
    }

}
