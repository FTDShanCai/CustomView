package com.example.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class MyView3 extends View {

    private Paint paint_line, paint_blue, paint;
    private int mwidth, mheight;

    public MyView3(Context context) {
        super(context);
        initPaint();
    }

    public MyView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mheight = MeasureSpec.getSize(heightMeasureSpec);
        mwidth = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(mwidth, mheight);
    }

    private void initPaint() {
        paint_line = new Paint();
        paint_line.setAntiAlias(true);
        paint_line.setStrokeWidth(10);
        paint_line.setStyle(Paint.Style.STROKE);
        paint_line.setColor(Color.rgb(241, 90, 36));

        paint_blue = new Paint();
        paint_blue.setAntiAlias(true);
        paint_blue.setStrokeWidth(10);
        paint_blue.setStyle(Paint.Style.STROKE);
        paint_blue.setColor(Color.rgb(36, 117, 196));


        paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setColor(Color.rgb(0, 0, 0));

    }

    private final float C = 0.551915024494f;

    @Override
    protected void onDraw(Canvas canvas) {
        int radius = 150;
        canvas.translate(mwidth / 2, mheight / 2);
        canvas.scale(1, -1);

        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Path path4 = new Path();


        canvas.drawLine(0, mheight / 2, 0, -mheight / 2, paint);
        canvas.drawLine(-mwidth / 2, 0, mwidth / 2, 0, paint);

        float[] point1 = {radius * C, radius};
        float[] point2 = {radius, radius * C};
        float[] point3 = {radius, 0};
        drawPoint(point1, canvas);
        drawPoint(point2, canvas);
        drawPoint(point3, canvas);

        path1.moveTo(0, radius);
        path1.cubicTo(point1[0], point1[1], point2[0], point2[1], point3[0], point3[1]);
        canvas.drawPath(path1, paint_blue);

        float[] point4 = {radius, -radius * C};
        float[] point5 = {radius * C, -radius};
        float[] point6 = {0, -radius};
        drawPoint(point4, canvas);
        drawPoint(point5, canvas);
        drawPoint(point6, canvas);
        path2.moveTo(radius, 0);
        path2.cubicTo(point4[0], point4[1], point5[0], point5[1], point6[0], point6[1]);
        canvas.drawPath(path2, paint_blue);

        float[] point7 = {-radius * C, -radius};
        float[] point8 = {-radius, -radius * C};
        float[] point9 = {-radius, 0};
        drawPoint(point7, canvas);
        drawPoint(point8, canvas);
        drawPoint(point9, canvas);
        path3.moveTo(0, -radius);
        path3.cubicTo(point7[0], point7[1], point8[0], point8[1], point9[0], point9[1]);
        canvas.drawPath(path3, paint_blue);

        float[] point10 = {-radius, radius * C};
        float[] point11 = {-radius * C, radius};
        float[] point12 = {0, radius};
        drawPoint(point10, canvas);
        drawPoint(point11, canvas);
        drawPoint(point12, canvas);
        path4.moveTo(-radius, 0);
        path4.cubicTo(point10[0], point10[1], point11[0], point11[1], point12[0], point12[1]);
        canvas.drawPath(path4, paint_blue);


//        canvas.drawCircle(0, 0, radius, paint_line);


    }

    private void drawPoint(float[] f, Canvas canvas) {
//        canvas.drawPoint(f[0], f[1], paint_line);
    }
}
