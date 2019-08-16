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
 * <p>description: 熟悉Canvas
 */
public class CanvasView extends View {
    private int width;
    private int height;

    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        Path clip_path = new Path();
//        clip_path.addCircle(width / 2, height / 2, 100, Path.Direction.CCW);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            canvas.clipOutPath(clip_path);
//        }
//        canvas.translate(width / 2, height / 2);
//        canvas.rotate(90);
//        canvas.drawRGB(42, 129, 197);
        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(Color.rgb(60, 63, 65));
        paint.setAntiAlias(true);
//        RectF rect = new RectF();
//        rect.set(width / 4, height / 4, width / 4 * 3, height / 4 * 3);
//        canvas.drawRoundRect(rect, 20, 20, paint);
        Path path = new Path();
        path.moveTo(0, height / 2);
        path.quadTo(width / 2, height - 10, width, height / 2);
        canvas.drawPath(path, paint);
    }

}
