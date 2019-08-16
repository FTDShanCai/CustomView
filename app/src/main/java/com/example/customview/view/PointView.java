package com.example.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import androidx.annotation.Nullable;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class PointView extends BaseView {

    private Paint paint_circle;
    private Region region_circle;


    private int radius = 30;
    private float x = -1;
    private float y = -1;

    private float touch_x;
    private float touch_y;


    private boolean isTouch;
    private int pointId;

    private float speed_x = 0;
    private float speed_y = 0;

    private float damping = 0.95f;//阻尼

    private boolean mXFixed, mYFixed;

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            x = x + speed_x / 30;
            y = y + speed_y / 30;

            speed_x = speed_x * damping;
            speed_y = speed_y * damping;

            if (Math.abs(speed_x) < 5) {
                speed_x = 0;
            }

            if (Math.abs(speed_y) < 5) {
                speed_y = 0;
            }

            resetArea();
            // 转向
            if (mXFixed) {
                speed_x = -speed_x;
            }

            if (mYFixed) {
                speed_y = -speed_y;
            }

            invalidate();
            if (speed_x == 0 && speed_y == 0) {
                handler.removeCallbacks(this);
                return;
            }
            handler.postDelayed(this, 33);
        }
    };


    private ScaleGestureDetector scaleGestureDetector;
    private GestureDetector gestureDetector;
    private GestureDetector.SimpleOnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (isTouch) {
                speed_x = velocityX;
                speed_y = velocityY;
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 0);
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }

    };

    private ScaleGestureDetector.OnScaleGestureListener scaleGestureListener = new ScaleGestureDetector.SimpleOnScaleGestureListener() {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scale = detector.getScaleFactor();
            Log.d("ftd", "scale:" + scale);
            int radius_scale = (int) (scale * radius);

            if (radius_scale > mwidth / 2) {
                radius = mwidth / 2;
            } else if (radius_scale < 30) {
                radius = 30;
            } else {
                radius = radius_scale;
            }
            invalidate();
            return true;
        }
    };

    public PointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint_circle = new Paint();
        paint_circle.setColor(Color.rgb(241, 90, 36));
        paint_circle.setAntiAlias(true);
        gestureDetector = new GestureDetector(getContext(), onGestureListener);
        scaleGestureDetector = new ScaleGestureDetector(getContext(), scaleGestureListener);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (x == -1 || y == -1) {
            x = mwidth / 2f;
            y = mheight / 2f;
        }

        region_circle = new Region();
        int left = (int) (x - radius);
        int top = (int) (y - radius);
        int right = (int) (x + radius);
        int bottom = (int) (y + radius);
        Region region = new Region(left, top, right, bottom);
        Path path = new Path();
        path.addCircle(x, y, radius, Path.Direction.CW);
        region_circle.setPath(path, region);
        if (isTouch) {
            paint_circle.setColor(Color.rgb(39, 124, 180));
        } else {
            paint_circle.setColor(Color.rgb(241, 90, 36));
        }
        canvas.drawCircle(x, y, radius, paint_circle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        gestureDetector.onTouchEvent(event);
        int e_x = (int) event.getX();
        int e_y = (int) event.getY();

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                if (region_circle.contains(e_x, e_y)) {
                    isTouch = true;
                    speed_x = 0;
                    speed_y = 0;
                    touch_x = e_x;
                    touch_y = e_y;
                    pointId = event.getActionIndex();
                    invalidate();
                }
                return true;
            case MotionEvent.ACTION_UP:
                isTouch = false;
                invalidate();
                return true;
            case MotionEvent.ACTION_POINTER_UP:
                if (event.getActionIndex() == pointId) {
                    isTouch = false;
                }
                return true;
            case MotionEvent.ACTION_POINTER_DOWN:
                int point1_x = (int) event.getX(event.getPointerId(1));
                int point1_y = (int) event.getY(event.getPointerId(1));
                if (region_circle.contains(point1_x, point1_y)) {

                }
                break;
            case MotionEvent.ACTION_MOVE:
//                if (event.getPointerCount() >= 2) {
//                    int point1 = event.getPointerId(0);
//                    int point2 = event.getPointerId(1);
//                    Log.d("ftd", "point1: x_" + event.getX(point1) + ",y_" + event.getY(point1));
//                    Log.d("ftd", "point2: x_" + event.getX(point2) + ",y_" + event.getY(point2));
//                }

                if (event.getPointerCount() > 2) {//双指
                    event.getX(0);


                } else if (isTouch) {//单点移动
                    this.x = (e_x - touch_x) + x;
                    this.y = (e_y - touch_y) + y;
                    resetArea();
                    touch_x = e_x;
                    touch_y = e_y;
                    invalidate();
                    return true;
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    //修正球的位置
    private void resetArea() {
        mXFixed = false;
        mYFixed = false;

        if (x - radius < 0) {
            x = radius;
            mXFixed = true;
        } else if (x + radius > mwidth) {
            x = mwidth - radius;
            mXFixed = true;
        }

        if (y - radius < 0) {
            y = radius;
            mYFixed = true;
        } else if (y + radius > mheight) {
            y = mheight - radius;
            mYFixed = true;
        }


    }


}
