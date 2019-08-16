package com.example.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class MyView2 extends View {
    Paint paint;

    int width;
    int height;

    public MyView2(Context context) {
        super(context);
        initPaint();
    }

    public MyView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private Path getPath() {
        Path path = new Path();
        path.moveTo(width / 4, height / 4);
        path.lineTo(width / 4 * 3, height / 4 * 3);
        return path;
    }


    private Path getPath2() {
        Path path = new Path();
        path.moveTo(width / 4 * 3, height / 4);
        path.lineTo(width / 4, height / 4 * 3);
        return path;
    }

    private Path getPath3() {
        Path path = new Path();
        RectF rectF = new RectF();
        rectF.set(width / 4, height / 4, width / 4 * 3, height / 4 * 3);
        path.addArc(rectF, 0, 90);
        return path;
    }


    private Path getPath4() {
        Path path = new Path();
        RectF rectF = new RectF();
        rectF.set(width / 4, height / 4, width / 4 * 3, height / 4 * 3);
        path.addArc(rectF, 180, 90);
        return path;
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.rgb(0, 0, 0));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(getPath(), paint);
        canvas.drawPath(getPath2(), paint);
        canvas.drawPath(getPath3(), paint);
        canvas.drawPath(getPath4(), paint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}
