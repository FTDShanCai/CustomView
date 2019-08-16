package com.example.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.customview.ScreenUtils;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description: 信用标签 2019年3月27日 16:36:33
 */
public class CreditInfoLableView extends View {
    private int mWidth;
    private int mHeight;

    private Paint paint_bg;
    private Paint paint_text;
    private Region[] regions;

    private int[][] icons; // 标签图标
    private String[] lableNames; //标签名称
    private int size = 0; //标签数量
    private float scale = 0.78f; //间距和图片比
    private int select_position = 0;//选中位置
    private int dp_15;
    private CreditInfoLableListener creditInfoLableListener;

    public void setCreditInfoLableListener(CreditInfoLableListener creditInfoLableListener) {
        this.creditInfoLableListener = creditInfoLableListener;
    }

    public CreditInfoLableView(Context context) {
        super(context);
        init();
    }

    public CreditInfoLableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = (int) (mWidth * 0.35);
        setMeasuredDimension(mWidth, mHeight);
    }


    /**
     * 设置数据
     *
     * @param icons      标签图标
     * @param lableNames 标签名称
     */
    public void setItems(int[][] icons, String[] lableNames) {
        if (icons == null || lableNames == null)
            return;

        if (icons.length != lableNames.length) {
            throw new IllegalArgumentException("icons.length != lableNames.length");
        }

        this.icons = icons;
        this.lableNames = lableNames;
        size = icons.length;
        regions = new Region[size];
        this.select_position = 0;
        invalidate();
    }


    private void init() {
        dp_15 = ScreenUtils.dip2px(getContext(), 15);

        paint_bg = new Paint();
        paint_bg.setStrokeWidth(1);
        paint_bg.setAntiAlias(true);
        paint_bg.setStyle(Paint.Style.FILL);
        paint_bg.setColor(Color.rgb(245, 245, 245));

        paint_text = new Paint();
        paint_text.setStrokeWidth(5);
        paint_text.setAntiAlias(true);
        paint_text.setColor(Color.rgb(99, 99, 99));
        paint_text.setTextSize(sp2px(getContext(), 13));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (icons == null || lableNames == null) {
            return;
        }

        float img_radius = mWidth * scale / size / 2;//图标的半径

        float[] startValue = {0, img_radius};
        float[] controlValue = {mWidth / 2f, mHeight / 4 * 3};
        float[] endValue = {mWidth, img_radius};


        Path path_bg = new Path();

        path_bg.moveTo(0, 0);
        path_bg.lineTo(startValue[0], startValue[1]);
        path_bg.quadTo(controlValue[0], controlValue[1], endValue[0], endValue[1]);
        path_bg.lineTo(mWidth, 0);
        path_bg.close();
        canvas.drawPath(path_bg, paint_bg);

//        Bitmap yinying = BitmapFactory.decodeResource(getResources(), R.drawable.bg_yinying);
//        if (yinying != null) {
//            yinying = getNewBitmap(yinying, mWidth);
//            canvas.drawBitmap(yinying, 0, -(img_radius), new Paint());
//        }

        int interval_size = size + 1; //间隔的数量
        float interval_lenth = mWidth * (1 - scale) / interval_size;

        for (int i = 0; i < size; i++) {
            //float p_x = oneMiunsT * oneMiunsT * startValue[0] + 2 * t * oneMiunsT * controlValue[0] + t * t * endValue[0];
            float p_x = i * img_radius * 2 + img_radius + (i + 1) * interval_lenth; //X 坐标
            float t = p_x / mWidth;
            float point_scale = 1 - t;
            float p_y = point_scale * point_scale * startValue[1] + 2 * t * point_scale * controlValue[1] + t * t * endValue[1];

            Bitmap icon = null;
            if (i == select_position) {
                paint_text.setColor(Color.rgb(33, 33, 33));
                Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
                paint_text.setTypeface(font);
                icon = BitmapFactory.decodeResource(getResources(), icons[i][0]);
            } else {
                paint_text.setColor(Color.rgb(99, 99, 99));
                Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL);
                paint_text.setTypeface(font);
                icon = BitmapFactory.decodeResource(getResources(), icons[i][1]);
            }

            Region region_icon = new Region();
            Path path_region = new Path();
            path_region.addCircle(p_x, p_y, img_radius, Path.Direction.CW);
            int left = (int) (p_x - img_radius);
            int top = (int) (p_y - img_radius);
            int right = (int) (p_x + img_radius);
            int bottom = (int) (p_y + img_radius);
            Region rect_region = new Region(left, top, right, bottom);
            region_icon.setPath(path_region, rect_region);
            regions[i] = region_icon;
            if (icon != null) {
                icon = getNewBitmap(icon, img_radius * 2);
                canvas.drawBitmap(icon, p_x - img_radius, p_y - img_radius, new Paint());
            }

            String text = lableNames[i];
            if (!TextUtils.isEmpty(text)) {
                Rect bounds = new Rect();
                paint_text.getTextBounds(text, 0, text.length(), bounds);
                float offset = (bounds.top + bounds.bottom) / 2f;
                float text_x = p_x - (bounds.right - bounds.left) / 2f;
                float text_y = p_y + img_radius + dp_15 + offset;
                canvas.drawText(text, text_x, text_y, paint_text);
            }

        }

//        drawBianKuang(canvas);
    }

    private void drawBianKuang(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(0, 0, 0));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(mWidth, 0);
        path.lineTo(mWidth, mHeight);
        path.lineTo(0, mHeight);
        path.close();
        canvas.drawPath(path, paint);
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

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    private int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();
                if (regions != null && regions.length != 0) {
                    for (int i = 0; i < regions.length; i++) {
                        Region region = regions[i];
                        if (region.contains(x, y)) {
                            if (select_position == i) {
                                break;
                            }

                            if (creditInfoLableListener != null) {
                                creditInfoLableListener.onLableClick(i, lableNames[i]);
                            }
                            select_position = i;
                            invalidate();
                            break;
                        }

                    }
                }
                break;
        }
        return true;
    }

    public interface CreditInfoLableListener {
        void onLableClick(int position, String text);
    }

    public int getSelect_position() {
        return select_position;
    }
}
