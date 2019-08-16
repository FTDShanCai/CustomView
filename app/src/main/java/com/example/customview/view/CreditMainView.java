package com.example.customview.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.customview.R;
import com.example.customview.ScreenUtils;

import java.util.Random;

import androidx.annotation.Nullable;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class CreditMainView extends View {
    private double rr = 2 * Math.PI / 60;//2π即360度的圆形分成60份
    private int mwidth;
    private int mheight;

    private final float circle_x_scale = 0.36f;
    private float circle_radius;
    private Paint paint_circle;
    private int dp_25, dp_10, dp_5;
    private Bitmap bg_bitmap;

    private int current_point = 0; //选中分数
    private int speed = 30; //加分速度

    private String ranking = "";  //排行
    private String next_credit = "";//信用升级差距
    private int my_point = 465; //我的分数
    private float total_point = 700; //总分

    public CreditMainView(Context context) {
        super(context);
        init();
    }

    public CreditMainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mwidth = MeasureSpec.getSize(widthMeasureSpec);
        mheight = MeasureSpec.getSize(heightMeasureSpec);
        if (bg_bitmap != null) {
            bg_bitmap = getNewBitmap(bg_bitmap, mwidth);
            mheight = bg_bitmap.getHeight();
        }
        setMeasuredDimension(mwidth, mheight);
    }

    private void init() {
        paint_circle = new Paint();
        paint_circle.setAntiAlias(true);
        paint_circle.setStrokeWidth(12);
        paint_circle.setStrokeCap(Paint.Cap.ROUND);

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        dp_25 = ScreenUtils.dip2px(getContext(), 25);
        dp_10 = ScreenUtils.dip2px(getContext(), 10);
        dp_5 = ScreenUtils.dip2px(getContext(), 5);
        bg_bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bg_credit);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("ftd", "ondraw");
        if (bg_bitmap != null) {
            canvas.drawBitmap(bg_bitmap, 0, 0, new Paint());
        }
        drawPointAngle(canvas);
        drawCenterText(canvas);
        drawRankingText(canvas);
        drawNextText(canvas);
        offsetPoint();
    }

    /**
     * 画分数
     *
     * @param canvas
     */
    private void drawPointAngle(Canvas canvas) {
        circle_radius = mwidth * circle_x_scale;
//        float circle_edge = mwidth * (0.5f - circle_x_scale);
        int current_index = (int) (current_point / total_point * 35);
        if (current_index > 35) {
            current_index = 35;
        }

        float w = mwidth / 2f;  //圈的圆心 X
        float h = dp_25 + circle_radius; //圈的圆心 Y

        for (int i = 0; i < 35; i++) {
            float x1, y1, x2, y2;//刻度的两端的坐标即起始于结束的坐标
            int index = i - 17; //偏移17个角度
            double du = rr * index;//当前所占的角度
            double sinx = Math.sin(du);//该角度的sin值
            double cosy = Math.cos(du);//该角度的cos值

            float wai_arcRa = circle_radius;
            if (i < current_index) {
                paint_circle.setColor(Color.rgb(182, 239, 210));
            } else if (i == current_index) {
                paint_circle.setColor(Color.rgb(182, 239, 210));
                wai_arcRa = circle_radius + dp_5;
            } else {
                paint_circle.setColor(Color.rgb(55, 185, 121));
            }

            x1 = (float) (w + wai_arcRa * sinx);
            y1 = (float) (h - wai_arcRa * cosy);

            float nei_arcRa = circle_radius - dp_10;
            x2 = (float) (w + nei_arcRa * sinx);
            y2 = (float) (h - nei_arcRa * cosy);
            canvas.drawLine(x1, y1, x2, y2, paint_circle);//通过两端点绘制刻度
        }
    }

    private void drawCenterText(Canvas canvas) {
        Paint paint_text = new Paint();
        try {
            Typeface font = Typeface.createFromAsset(getResources().getAssets(), "AkzidenzGrotesk-MediumCond.otf");
            paint_text.setTypeface(font);
        } catch (Exception e) {

        }
        paint_text.setColor(Color.WHITE);
        paint_text.setTextAlign(Paint.Align.CENTER); //设置文字居中
        paint_text.setTextSize(sp2px(getResources(), 70F));
        float dp5 = ScreenUtils.dip2px(getContext(), 3.0F);
        paint_text.setShadowLayer(dp5, -5, 5, Color.parseColor("#999999"));

        String text = current_point + "";
        if (!TextUtils.isEmpty(text)) {
            float text_x = mwidth / 2f;
            float text_y = circle_radius + dp_25;
            canvas.drawText(text, text_x, text_y, paint_text);
        }
    }


    private void drawRankingText(Canvas canvas) {
        Paint paint_text = new Paint();
        paint_text.setColor(Color.WHITE);
        paint_text.setTextAlign(Paint.Align.CENTER); //设置文字居中
        paint_text.setTextSize(sp2px(getResources(), 13F));

        String text = ranking;
        if (!TextUtils.isEmpty(text)) {
            float text_x = mwidth / 2f;
            float text_y = mheight * 0.33f;
            canvas.drawText(text, text_x, text_y, paint_text);
        }
    }

    private void drawNextText(Canvas canvas) {
        Paint paint_text = new Paint();
        paint_text.setColor(Color.WHITE);
        paint_text.setTextAlign(Paint.Align.CENTER); //设置文字居中
        paint_text.setTextSize(sp2px(getResources(), 13F));

        String text = next_credit;
        if (!TextUtils.isEmpty(text)) {
            float text_x = mwidth / 2f;
            float text_y = circle_radius + dp_25 * 2;
            canvas.drawText(text, text_x, text_y, paint_text);
        }
    }

    private void offsetPoint() {
        if (current_point != my_point) {
            current_point = current_point + speed;
            if (current_point > my_point) {
                current_point = my_point;
            } else {
                speed = (int) (speed * 0.97);
                if (speed < 10) {
                    Random random = new Random();
                    speed = random.nextInt(10);
                }
            }
            postInvalidateDelayed(100);
        }
    }

    public Bitmap getNewBitmap(Bitmap bitmap, float newWidth) {
        // 获得图片的宽高.
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // 计算缩放比例.
        float scale = newWidth / width;
        // 取得想要缩放的matrix参数.
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        // 得到新的图片.
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return newBitmap;
    }

    public float sp2px(Resources resources, float sp) {
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale + 0.5f;
    }


    public void setData(int my_point, int total_point, String ranking, String next_credit) {
        this.total_point = total_point;
        this.my_point = my_point;
        this.ranking = ranking;
        this.next_credit = next_credit;
        resetValue();
        invalidate();
    }

    private void resetValue() {
        Log.d("ftd", "resetValue");
        speed = 30;
        current_point = 0;
    }
}
