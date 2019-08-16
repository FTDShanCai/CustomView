package com.example.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class TestView extends BaseView {
    private Paint paint;//画笔
    private double rr = 2 * Math.PI / 60;//2π即360度的圆形分成60份,一秒钟与一分钟
    private float arcRa = 250;//圆半径

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint = new Paint();//初始化画笔
        paint.setStrokeWidth(10);
        drawTest(canvas);
    }

//    private void drawTest(Canvas canvas) {
//        for (int i = 0; i < 35; i++) {///2π圆形分成60份,一秒钟与一分钟,所以要绘制60次,这里是从0到59
//            float x1, y1, x2, y2;//刻度的两端的坐标即起始于结束的坐标
//            double du = Math.toRadians(6d * i - 135d);
//            double sinx = Math.sin(du);//该角度的sin值
//            double cosy = Math.cos(du);//该角度的cos值
//            float nei_arcRa = arcRa;
//            x1 = (float) (nei_arcRa + nei_arcRa * sinx);//以默认坐标系通过三角函数算出刻度离圆心最远的端点的x轴坐标
//            y1 = (float) (nei_arcRa - nei_arcRa * cosy);//以默认坐标系通过三角函数算出刻度离圆心最远的端点的y轴坐标
//            float wai_arcRa = arcRa;
//            if (i == 0) {
//                wai_arcRa = arcRa + 20;
//            }
//            x2 = (float) (wai_arcRa + wai_arcRa * sinx);//以默认坐标系通过三角函数算出该刻度离圆心最近的端点的x轴坐标
//            y2 = (float) (wai_arcRa - wai_arcRa * cosy);//以默认坐标系通过三角函数算出该刻度离圆心最近的端点的y轴坐标
//            canvas.drawLine(x1, y1, x2, y2, paint);//通过两端点绘制刻度
//        }
//    }


    private void drawTest(Canvas canvas) {
        int w = mwidth / 2;  //圈的圆心
        int h = mheight / 2; //圈的圆心
        //围绕圆形绘制刻度,坐标原点默认在手机屏幕左上角
        for (int i = 0; i < 35; i++) {///2π圆形分成60份,一秒钟与一分钟,所以要绘制60次,这里是从0到59
            float x1, y1, x2, y2;//刻度的两端的坐标即起始于结束的坐标
            int index = i - 17; //偏移17个角度
            double du = rr * index;//当前所占的角度
            double sinx = Math.sin(du);//该角度的sin值
            double cosy = Math.cos(du);//该角度的cos值
            float nei_arcRa = arcRa;
            if (index == 3) {
                nei_arcRa = arcRa + 20;
            }

            x1 = (float) (w + nei_arcRa * sinx);//以默认坐标系通过三角函数算出刻度离圆心最远的端点的x轴坐标
            y1 = (float) (h - nei_arcRa * cosy);//以默认坐标系通过三角函数算出刻度离圆心最远的端点的y轴坐标


            float wai_arcRa = 5 * arcRa / 6;
            x2 = (float) (w + wai_arcRa * sinx);//以默认坐标系通过三角函数算出该刻度离圆心最近的端点的x轴坐标
            y2 = (float) (h - wai_arcRa * cosy);//以默认坐标系通过三角函数算出该刻度离圆心最近的端点的y轴坐标
            canvas.drawLine(x1, y1, x2, y2, paint);//通过两端点绘制刻度
        }
    }

}
