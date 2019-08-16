package com.example.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class MyView4 extends BaseView {
    private Paint mDeafultPaint;


    public MyView4(Context context) {
        super(context);
    }

    public MyView4(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mDeafultPaint = new Paint();
        mDeafultPaint.setColor(Color.rgb(0, 0, 0));
        canvas.translate(mwidth / 2, mheight / 2);
        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Path path4 = new Path();
        path1.addCircle(0, 0, 200, Path.Direction.CW);
        path2.addRect(0, -200, 200, 200, Path.Direction.CW);
        path3.addCircle(0, -100, 100, Path.Direction.CW);
        path4.addCircle(0, 100, 100, Path.Direction.CCW);

        path1.op(path2, Path.Op.REVERSE_DIFFERENCE);
//        path1.op(path3, Path.Op.UNION);
//        path1.op(path4, Path.Op.DIFFERENCE);
        canvas.drawPath(path1, mDeafultPaint);




    }
}
