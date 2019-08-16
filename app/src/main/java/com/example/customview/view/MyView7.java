package com.example.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.example.customview.R;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class MyView7 extends BaseView {
    protected int mwidth;
    protected int mheight;

    private Bitmap photo;

    public MyView7(Context context) {
        this(context, null);
    }

    public MyView7(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        photo = BitmapFactory.decodeResource(getResources(), R.mipmap.photo);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mwidth = MeasureSpec.getSize(widthMeasureSpec);
        mheight = MeasureSpec.getSize(heightMeasureSpec);
        if (photo != null) {
            mheight = photo.getHeight();
        }
        setMeasuredDimension(mwidth, mheight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (photo == null)
            return;

        int photo_width = photo.getWidth();
        canvas.drawBitmap(photo, mwidth / 2f - photo_width / 2f, 0, null);
    }
}
