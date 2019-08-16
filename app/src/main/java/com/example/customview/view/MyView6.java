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
public class MyView6 extends View {

    private int mWidth, mHeight;

    private Paint green_paint;
    private Paint grey_paint;
    private int radius = 350;
    private int edge_size = 5;  //边的数量
    private float edge_angle = 0; //边的角度
    private double[] points = {0.5, 0.8, 0.7, 0.5, 1};
    private float sin;
    private float cos;

    public MyView6(Context context) {
        super(context);
        init();
    }

    public MyView6(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        green_paint = new Paint();
        green_paint.setColor(Color.rgb(94, 207, 177));
//        green_paint.setStyle(Paint.Style.FILL);

        grey_paint = new Paint();
        grey_paint.setColor(Color.rgb(235, 235, 235));
        grey_paint.setStrokeWidth(5);

        edge_angle = 360f / edge_size;
        setBackgroundColor(Color.rgb(255, 255, 255));
        double radians = Math.toRadians(edge_angle / 2);
        sin = (float) Math.sin(radians);
        cos = (float) Math.cos(radians);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mWidth / 2f, mHeight / 2f);
        canvas.scale(1, -1);

        int radius_4 = radius / 4;
        int radius_total = radius_4;
        for (int y = 0; y < 4; y++) {
            float line_x = sin * radius_total;
            float line_y = cos * radius_total;
            for (int i = 0; i < edge_size; i++) {
                canvas.drawLine(line_x, -line_y, -line_x, -line_y, grey_paint);
                canvas.rotate(edge_angle);
            }
            radius_total = radius_total + radius_4;
        }

        canvas.drawPoint(0, 0, grey_paint);

        Path path = new Path();
        for (int i = 0; i < points.length; i++) {
            float y = (float) (radius * points[i]);
            if (i == 0) {
                path.moveTo(0, y);
            } else {
                path.lineTo(0, y);
            }
            canvas.drawCircle(0, y, 5, green_paint);
            canvas.rotate(edge_angle);
        }
        path.lineTo(200, 200);
        path.close();
        canvas.drawPath(path, green_paint);
    }
}
