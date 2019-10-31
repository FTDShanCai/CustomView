package com.example.customview.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingParent;

import com.example.customview.AbilityBean;
import com.example.customview.R;
import com.example.customview.ScreenUtils;

import java.util.ArrayList;

/**
 * @author sunwei
 * email：tianmu19@gmail.com
 * date：2019/3/25 pic_1:23
 * package：me.tmgg.viewsdemoapp.widgets.ability
 * version：1.0
 * <p>description：   蛛网（多项值）分析图           </p>
 */
public class AbilityMapView extends View implements NestedScrollingParent {
    private final static int NUMBER_OF_EDGES = 5;
    private final static float DEFAULT_TEXTSIZE = 14F;
    private final static int POLYGON_COUNTS = 5;

    private ArrayList<AbilityBean> dataLists; //元数据
    private int n; //边的数量或者能力的个数
    private float Radius; //最外圈的半径，顶点到中心点的距离
    private int intervalCount; //间隔数量，就把半径分为几段
    private float angle; //两条顶点到中线点的线之间的角度
    private Paint linePaint; //画线的笔
    private Paint textPaint; //画文字的笔
    private int viewHeight; //控件宽度
    private int viewWidth; //控件高度
    private ArrayList<ArrayList<PointF>> pointsArrayList; //存储多边形顶点数组的数组
    private ArrayList<PointF> abilityPoints; //存储能力点的数组

    private Bitmap view_bg;

    //////////////////////////////////////////////////////
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

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes) {
        return false;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes) {
        Log.d("ftd", "onNestedScrollAccepted");
    }
    @Override
    public void onStopNestedScroll(@NonNull View target) {

    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed) {

    }

    @Override
    public boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return 0;
    }

    public interface CreditInfoLableListener {
        void onLableClick(int position, String text);
    }

    public AbilityMapView(Context context) {
        this(context, null);
    }

    public AbilityMapView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AbilityMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSize(context);
        initPoints();
        initPaint(context);
        view_bg = BitmapFactory.decodeResource(getResources(), R.mipmap.bg_top);
    }

    /**
     * 传入元数据
     *
     * @param datas
     */
    public void setData(ArrayList<AbilityBean> datas) {

        if (datas == null) {
            return;
        }
        this.dataLists = datas;
        n = datas.size();
        //View本身调用迫使view重画
        invalidate();
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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("ftd", "onMeasure");
        viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (view_bg != null) {
            view_bg = getNewBitmap(view_bg, viewWidth);
            viewHeight = view_bg.getHeight();
            Radius = 0.598f * viewHeight / 2;
            Log.d("ftd", "Radius  onMeasure:" + Radius);
        }

        if (viewHeight != 0) {
            viewHeight = (int) (viewHeight * 1.136);
        }
        setMeasuredDimension(viewWidth, viewHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initSize(getContext());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (dataLists != null && dataLists.size() != 0) {
            if (view_bg != null) {
                canvas.drawBitmap(view_bg, 0, 0, new Paint());
            }
            canvas.save();
            //把画布的原点移动到控件的中心点
            canvas.translate(viewWidth / 2f, viewHeight * 0.436f);
//        //多边形
//        drawPolygon(canvas);
            //边线
//        drawOutLine(canvas);
            //画中心点到边角的线
//        drawCenter2Edgepoints(canvas);
            //能力线
            drawAbilityLine(canvas);
            //文字
            drawAbilityText(canvas);
//        //画文字上图片
            drawIconsAboveTexts(canvas);
            //画中心文字
            drawCenterText(canvas);
            canvas.restore();
        }
        drawLables(canvas);
    }

    private void drawBianKuang(Canvas canvas) {
        canvas.translate(-viewWidth / 2f, -viewHeight / 2f);
        Paint paint = new Paint();
        paint.setColor(Color.rgb(0, 0, 0));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(viewWidth, 0);
        path.lineTo(viewWidth, viewHeight);
        path.lineTo(0, viewHeight);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawCenterText(Canvas canvas) {
        canvas.save();
        float total = 0;
        for (AbilityBean bean : dataLists) {
            total = total + bean.getValue();
        }

        if (total < 0) {
            total = 0;
        }

//        String text = ClearMoreZeroUtil.subZeroAndDot(total + "");
        String text = total + "";
        Paint centerTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        try {
            Typeface font = Typeface.createFromAsset(getResources().getAssets(), "AkzidenzGrotesk-MediumCond.otf");
            centerTextPaint.setTypeface(font);
        } catch (Exception e) {

        }
        centerTextPaint.setColor(Color.WHITE);
        centerTextPaint.setTextAlign(Paint.Align.CENTER); //设置文字居中
        centerTextPaint.setTextSize(ScreenUtils.sp2px(getContext().getResources(), 60F));
        float dp5 = ScreenUtils.dp2px(getContext().getResources(), 0.5F);
        centerTextPaint.setShadowLayer(dp5, 0, 8, Color.argb(130, 11, 93, 52));

        Paint.FontMetrics fontMetrics = centerTextPaint.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        canvas.translate(0, 0);
        int baseLineY = (int) (0 - top / 2 - bottom / 2);//基线中间点的y轴计算公式
        canvas.drawText(text, 0
                , baseLineY, centerTextPaint);
        canvas.restore();
    }

    private void drawIconsAboveTexts(Canvas canvas) {
        if (dataLists == null || dataLists.size() <= 0) return;
        canvas.save();
        ArrayList<PointF> iconsPoints = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            float r = Radius;

            if (i == 0) {
                r += viewWidth * 0.04f;
            } else if (i == 1 || i == 4) {
                r += viewWidth * 0.035f;
            } else if (i == 2 || i == 3) {
                r += viewWidth * 0.035f;
            }

            float x = (float) (r * Math.cos(angle * i - Math.PI / 2));
            float y = (float) (r * Math.sin(angle * i - Math.PI / 2));

            if (i == 2) {
                x += viewWidth * 0.015f;
            } else if (i == 3) {
                x -= viewWidth * 0.015f;
            }
            iconsPoints.add(new PointF(x, y));
        }
        Paint photoPaint = new Paint();
        photoPaint.setDither(true);
        photoPaint.setFilterBitmap(true);

        for (int i = 0; i < n; i++) {
            float x = iconsPoints.get(i).x;
            Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), dataLists.get(i).getIcon());
            int iconHeight = bitmap.getHeight();
            int iconWidth = bitmap.getWidth();
            float textHeight = getTextHeight(dataLists.get(i).getDesc());
            float y = iconsPoints.get(i).y;
            if (y > 0) {
                y -= (float) iconHeight + textHeight / 2;
            } else {
                y -= (float) iconHeight + textHeight / 2;
            }

            if (0 == i) {
                x -= (float) iconWidth / 2;
            } else {
                if (x < 0) {
                    x -= (float) iconWidth;
                }
            }

            canvas.drawBitmap(bitmap, x, y, photoPaint);
        }

        canvas.restore();
    }

    private static final String TAG = "ability";

    private void drawAbilityText(Canvas canvas) {
        if (dataLists == null || dataLists.size() <= 0) return;

        canvas.save();
        ArrayList<PointF> textPoints = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            float r = Radius;

            if (i == 0) {
                r += viewWidth * 0.04f;
            } else if (i == 1 || i == 4) {
                r += viewWidth * 0.035f;
            } else if (i == 2 || i == 3) {
                r += viewWidth * 0.035f;
            }

            float x = (float) (r * Math.cos(angle * i - Math.PI / 2));
            float y = (float) (r * Math.sin(angle * i - Math.PI / 2));

            if (i == 2) {
                x += viewWidth * 0.015f;
            } else if (i == 3) {
                x -= viewWidth * 0.015f;
            }
            textPoints.add(new PointF(x, y));
        }
        if (dataLists.size() < n) {
            throw new IndexOutOfBoundsException("数据少于多边形的边数.");
        }

        textPaint.setTextSize(sp2px(getResources(), 12));
        textPaint.setColor(Color.rgb(255, 255, 255));
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        for (int j = 0; j < n; j++) {
            float x = textPoints.get(j).x;
            float y = textPoints.get(j).y;
            //图片
            Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), dataLists.get(j).getIcon());
            int iconWidth = bitmap.getWidth();
            String textContent = dataLists.get(j).getDesc();
            float textHeight = getTextHeight(textContent);

            y += textHeight / 2;
            if (0 != j) {
                if (x > 0) {
                    x += (float) iconWidth / 2;
                } else {
                    x -= (float) iconWidth / 2;
                }
            }
            canvas.drawText(textContent, x, y, textPaint);
        }
        canvas.restore();
    }

    private void drawAbilityLine(Canvas canvas) {
        canvas.save();

        abilityPoints = new ArrayList<>();
        if (dataLists.size() < n) {
            throw new IndexOutOfBoundsException("数据少于多边形的边数.");
        }
        float x1 = 0;
        float y1 = 0;
        for (int i = 0; i < n; i++) {

            float totalValue = dataLists.get(i).getTotalValue();
            float value = dataLists.get(i).getValue();

            if (totalValue < 0) {
                totalValue = 0;
            }
            if (value < 0) {
                value = 0;
            }

            float offsetTotalValue = totalValue / 3 * 5;
            float offsetValue = value + offsetTotalValue / 5 * 2;
            float scale = offsetValue / offsetTotalValue;
            if (scale > 1) {
                scale = 1;
            } else if (scale < 0) {
                scale = 0;
            }
            Log.d("ftd", "scale:" + scale + "  |  " + dataLists.get(i).getDesc());
            float r = Radius * scale;
            float x = (float) (r * Math.cos(angle * i - Math.PI / 2));
            float y = (float) (r * Math.sin(angle * i - Math.PI / 2));
            abilityPoints.add(new PointF(x, y));
            if (i == 0) {
                x1 = x;
                y1 = y;
            }
        }
        linePaint.setStrokeWidth(ScreenUtils.dp2px(getContext().getResources(), 1f));
        linePaint.setColor(Color.parseColor("#BDF4D4"));
        linePaint.setStyle(Paint.Style.FILL); //设置空心的

        int colors[] = new int[3];
        float positions[] = new float[3];

        // 第1个点
        colors[0] = 0xFFBDF4D4;
        positions[0] = 0;

        // 第2个点
        colors[1] = 0xFF27C084;
        positions[1] = 0.5f;


        LinearGradient shader = new LinearGradient(
                x1, y1,
                viewWidth, viewHeight,
                colors,
                positions,
                Shader.TileMode.CLAMP);
        linePaint.setShader(shader);


        Path path = new Path();
        boolean isMove = false;
        for (int i = 0; i < n; i++) {
            float x = abilityPoints.get(i).x;
            float y = abilityPoints.get(i).y;
            if (x == 0 && y == 0) {

            } else {
                if (!isMove) {
                    path.moveTo(x, y);
                    isMove = true;
                } else {
                    path.lineTo(x, y);
                }
            }

        }
        //别忘了闭合
        path.close();
        linePaint.setPathEffect(new CornerPathEffect(10));
        canvas.drawPath(path, linePaint);
        linePaint.setShader(null);
        linePaint.setPathEffect(null);
        canvas.restore();
    }

    private void drawCenter2Edgepoints(Canvas canvas) {
        if (dataLists == null || dataLists.size() <= 0) return;

        canvas.save();
        linePaint.setColor(Color.argb(153, 145, 247, 197));
        linePaint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < n; i++) {
            float x = pointsArrayList.get(0).get(i).x;
            float y = pointsArrayList.get(0).get(i).y;
            canvas.drawLine(0, 0, x, y, linePaint);
        }
        canvas.restore();
    }

    private void drawOutLine(Canvas canvas) {
        canvas.save();
        linePaint.setPathEffect(new CornerPathEffect(10));
        linePaint.setStyle(Paint.Style.STROKE);  //设置空心的
        linePaint.setColor(Color.argb(153, 145, 247, 197));
        Path path = new Path();
        for (int k = 0; k < intervalCount; k++) {
            for (int p = 0; p < n; p++) {
                float x = pointsArrayList.get(k).get(p).x;
                float y = pointsArrayList.get(k).get(p).y;
                if (p == 0) {
                    path.moveTo(x, y);
                } else {
                    path.lineTo(x, y);
                }
            }
            path.close();  //设置为闭合的
            canvas.drawPath(path, linePaint);
            path.reset();   //清除path存储的路径
        }
        canvas.restore();
        linePaint.setPathEffect(null);
        linePaint.setStyle(Paint.Style.FILL);
    }

    private void drawPolygon(Canvas canvas) {
        canvas.save();
        linePaint.setStyle(Paint.Style.FILL);  //设置为填充且描边
        Path path = new Path();
        //1.每层的颜色
        for (int k = 0; k < intervalCount; k++) {
            linePaint.setColor(Color.parseColor("#ffffff"));
            for (int p = 0; p < n; p++) {
                float x = pointsArrayList.get(k).get(p).x;
                float y = pointsArrayList.get(k).get(p).y;
                if (p == 0) {
                    path.moveTo(x, y);
                } else {
                    path.lineTo(x, y);
                }
            }
            path.close();  //设置为闭合的
            canvas.drawPath(path, linePaint);
            path.reset();   //清除path存储的路径
        }


        canvas.restore();
    }

    private void initPaint(Context context) {
        //1.linepaint
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(ScreenUtils.dp2px(context.getResources(), 1.0F));
        //2.textpaint
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextAlign(Paint.Align.CENTER); //设置文字居中
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(ScreenUtils.sp2px(context.getResources(), DEFAULT_TEXTSIZE));

//////////////////////////////////////
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
        paint_text.setTextSize(sp2px(getResources(), 13));
    }

    private void initPoints() {
        //一个数组中每个元素又一是一个点数组,有几个多边形就有几个数组
        pointsArrayList = new ArrayList<>();
        float x;
        float y;
        float r;
        for (int i = 0; i < intervalCount; i++) {

            //创建一个存储点的数组
            ArrayList<PointF> points = new ArrayList<>();
            if (n <= 0) return;
            for (int j = 0; j < n; j++) {
                r = Radius * ((float) (intervalCount - i) / intervalCount);
                //每一圈的半径都按比例减少 //这里减去Math.PI /
                // 2 是为了让多边形逆时针旋转90度，所以后面的所有用到cos,sin的都要减
                x = (float) (r * Math.cos(j * angle - Math.PI / 2));
                y = (float) (r * Math.sin(j * angle - Math.PI / 2));
                points.add(new PointF(x, y));
            }
            pointsArrayList.add(points);
        }

    }

    private void initSize(Context context) {
        n = NUMBER_OF_EDGES;
        if (Radius == 0) {
            Radius = ScreenUtils.dp2px(context.getResources(), 100);
        }
        Log.d("ftd", "Radius111:" + Radius);
        intervalCount = POLYGON_COUNTS;
        angle = (float) ((2 * Math.PI) / n);
    }


    private float getTextHeight(String text) {
        Paint.FontMetrics fm = textPaint.getFontMetrics();
        return (float) Math.ceil(fm.descent - fm.ascent);
    }

    private float getTextWidth(String text) {
        float textWidth = textPaint.measureText(text);
        return textWidth;
    }

    public float sp2px(Resources resources, float sp) {
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale + 0.5f;
    }


    ///////////////////////////////////////////////////
    public int getSelect_position() {
        return select_position;
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

    private void drawLables(Canvas canvas) {
        if (icons == null || lableNames == null) {
            return;
        }

        float img_radius = viewWidth * scale / size / 2;//图标的半径

        float[] startValue = {0, viewHeight * 0.8f};
        float[] controlValue = {viewWidth / 2f, view_bg.getHeight() + img_radius};
        float[] endValue = {viewWidth, viewHeight * 0.8f};


//        Path path_bg = new Path();
//
//        path_bg.moveTo(0, 0);
//        path_bg.lineTo(startValue[0], startValue[1]);
//        path_bg.quadTo(controlValue[0], controlValue[1], endValue[0], endValue[1]);
//        path_bg.lineTo(viewWidth, 0);
//        path_bg.close();
//        canvas.drawPath(path_bg, paint_bg);

//        Bitmap yinying = BitmapFactory.decodeResource(getResources(), R.drawable.bg_yinying);
//        if (yinying != null) {
//            yinying = getNewBitmap(yinying, mWidth);
//            canvas.drawBitmap(yinying, 0, -(img_radius), new Paint());
//        }

        int interval_size = size + 1; //间隔的数量
        float interval_lenth = viewWidth * (1 - scale) / interval_size;

        for (int i = 0; i < size; i++) {
            //float p_x = oneMiunsT * oneMiunsT * startValue[0] + 2 * t * oneMiunsT * controlValue[0] + t * t * endValue[0];
            float p_x = i * img_radius * 2 + img_radius + (i + 1) * interval_lenth; //X 坐标
            float t = p_x / viewWidth;
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

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d("ftd", "onFinishInflate");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d("ftd", "onAttachedToWindow");
    }
}
