package com.ddc.guide.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

import com.ddc.guide.animation.AnimationFactory;
import com.ddc.guide.animation.AnimationListener;
import com.ddc.guide.animation.MaterialIntroListener;
import com.ddc.guide.prefs.PreferencesManager;
import com.ddc.guide.shape.GuideView;
import com.ddc.guide.shape.TargetShape;
import com.ddc.guide.utils.Constants;

import java.util.ArrayList;
import java.util.List;


public class MaterialIntroView extends ConstraintLayout {
    /**
     * Mask color
     */
    private int maskColor;

    /**
     * MaterialIntroView will start
     * showing after delayMillis seconds
     * passed
     */
    private long delayMillis;

    /**
     * We don't draw MaterialIntroView
     * until isReady field set to true
     */
    private boolean isReady;

    /**
     * Show/Dismiss MaterialIntroView
     * with fade in/out animation if
     * this is enabled.
     */
    private boolean isFadeAnimationEnabled;

    /**
     * Animation duration
     */
    private long fadeAnimationDuration;

    private List<GuideView> grGuideViews;

    private int currentGuidePosition = 0;

    /**
     * Eraser
     */
    private Paint eraser;

    /**
     * Handler will be used to
     * delay MaterialIntroView
     */
    private Handler handler;

    /**
     * All views will be drawn to
     * this bitmap and canvas then
     * bitmap will be drawn to canvas
     */
    private Bitmap bitmap;
    private Canvas canvas;

    /**
     * Layout width/height
     */
    private int width;
    private int height;


    /**
     * Save/Retrieve status of MaterialIntroView
     * If Intro is already learnt then don't show
     * it again.
     */
    private PreferencesManager preferencesManager;

    /**
     * Check using this Id whether user learned
     * or not.
     */
    private String materialIntroViewId;

    /**
     * When layout completed, we set this true
     * Otherwise onGlobalLayoutListener stuck on loop.
     */
    private boolean isLayoutCompleted;

    /**
     * Notify user when MaterialIntroView is dismissed
     */
    private MaterialIntroListener materialIntroListener;

    /**
     * Disallow this MaterialIntroView from showing up more than once at a time
     */
    private boolean isIdempotent;


    public MaterialIntroView(Context context) {
        super(context);
        init(context);
    }

    public MaterialIntroView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MaterialIntroView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        if (getId() == View.NO_ID) {
            setId(ViewCompat.generateViewId());
        }

        setWillNotDraw(false);
        setVisibility(INVISIBLE);

        /**
         * set default values
         */
        maskColor = Constants.DEFAULT_MASK_COLOR;
        delayMillis = Constants.DEFAULT_DELAY_MILLIS;
        fadeAnimationDuration = Constants.DEFAULT_FADE_DURATION;
        isReady = false;
        isFadeAnimationEnabled = true;
        isLayoutCompleted = false;
        isIdempotent = false;

        /**
         * initialize objects
         */
        handler = new Handler();

        preferencesManager = new PreferencesManager(context);

        eraser = new Paint();
        eraser.setColor(0xFFFFFFFF);
        eraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        eraser.setFlags(Paint.ANTI_ALIAS_FLAG);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                boolean isLayoutFinish = true;
                GuideView grGuideView = grGuideViews.get(currentGuidePosition);
                for (TargetShape targetShape : grGuideView.getTargetShapes()) {
                    if (targetShape.getTarget().getPoint().y == 0) {
                        isLayoutFinish = false;
                        break;
                    }
                }
                if (isLayoutFinish && !isLayoutCompleted) {
                    isLayoutCompleted = true;
                    post(() -> setViews());
                }
            }
        });

    }

    private void setViews() {
        removeAllViews();
        GuideView grGuideView = grGuideViews.get(currentGuidePosition);
        for (TargetShape targetShape : grGuideView.getTargetShapes()) {
            targetShape.initViewShape(this);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void removeOnGlobalLayoutListener(View v, ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (Build.VERSION.SDK_INT < 16) {
            v.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
        } else {
            v.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isReady) return;

        if (bitmap == null || canvas == null) {
            if (bitmap != null) bitmap.recycle();

            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            this.canvas = new Canvas(bitmap);
        }

        this.canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        this.canvas.drawColor(maskColor);

        if (grGuideViews == null || grGuideViews.isEmpty()) return;
        GuideView guideView = grGuideViews.get(currentGuidePosition);

        List<TargetShape> targetShapes = guideView.getTargetShapes();
        for (TargetShape targetShape : targetShapes) {
            targetShape.draw(this.canvas, eraser);
        }

        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    private void show(Activity activity) {

        if (preferencesManager.isDisplayed(materialIntroViewId))
            return;

        ((ViewGroup) activity.getWindow().getDecorView()).addView(this);
        setReady();
        handler.postDelayed(() -> {
            if (isFadeAnimationEnabled)
                AnimationFactory.animateFadeIn(MaterialIntroView.this, fadeAnimationDuration, () -> setVisibility(VISIBLE));
            else
                setVisibility(VISIBLE);
        }, delayMillis);

        if (isIdempotent) {
            preferencesManager.setDisplayed(materialIntroViewId);
        }
    }

    /**
     * Dismiss Material Intro View
     */
    public void dismiss() {
        int size = grGuideViews.size();
        if (currentGuidePosition + 1 != size) {
            currentGuidePosition++;
            isLayoutCompleted = false;
            requestLayout();
            return;
        }


        if (!isIdempotent) {
            preferencesManager.setDisplayed(materialIntroViewId);
        }

        AnimationFactory.animateFadeOut(this, fadeAnimationDuration, new AnimationListener.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd() {
                setVisibility(GONE);
                removeMaterialView();

                if (materialIntroListener != null)
                    materialIntroListener.onUserClicked(materialIntroViewId);
            }
        });
    }

    private void removeMaterialView() {
        if (getParent() != null)
            ((ViewGroup) getParent()).removeView(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            dismiss();
        }
        return true;
    }

    private void setMaskColor(int maskColor) {
        this.maskColor = maskColor;
    }

    private void setDelay(int delayMillis) {
        this.delayMillis = delayMillis;
    }

    private void enableFadeAnimation(boolean isFadeAnimationEnabled) {
        this.isFadeAnimationEnabled = isFadeAnimationEnabled;
    }

    private void setReady() {
        this.isReady = true;
    }

    private void addGuideView(GuideView guideView) {
        if (grGuideViews == null) grGuideViews = new ArrayList<>();
        grGuideViews.add(guideView);
    }


    private void setIdempotent(boolean idempotent) {
        this.isIdempotent = idempotent;
    }

    private void setUsageId(String materialIntroViewId) {
        this.materialIntroViewId = materialIntroViewId;
    }

    private void setListener(MaterialIntroListener materialIntroListener) {
        this.materialIntroListener = materialIntroListener;
    }

    public static class Builder {

        private MaterialIntroView materialIntroView;

        private Activity activity;

        public Builder(Activity activity) {
            this.activity = activity;
            materialIntroView = new MaterialIntroView(activity);
        }

        public Builder setMaskColor(int maskColor) {
            materialIntroView.setMaskColor(maskColor);
            return this;
        }

        public Builder setDelayMillis(int delayMillis) {
            materialIntroView.setDelay(delayMillis);
            return this;
        }

        public Builder enableFadeAnimation(boolean isFadeAnimationEnabled) {
            materialIntroView.enableFadeAnimation(isFadeAnimationEnabled);
            return this;
        }

        public Builder addGuideView(GuideView guideView) {
            materialIntroView.addGuideView(guideView);
            return this;
        }

        public Builder setUsageId(String materialIntroViewId) {
            materialIntroView.setUsageId(materialIntroViewId);
            return this;
        }


        public Builder setIdempotent(boolean idempotent) {
            materialIntroView.setIdempotent(idempotent);
            return this;
        }

        public Builder setListener(MaterialIntroListener materialIntroListener) {
            materialIntroView.setListener(materialIntroListener);
            return this;
        }

        public MaterialIntroView build() {
            return materialIntroView;
        }

        public MaterialIntroView show() {
            build().show(activity);
            return materialIntroView;
        }
    }
}
