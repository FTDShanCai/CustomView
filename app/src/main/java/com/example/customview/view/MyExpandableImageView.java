package com.example.customview.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.expandable.ExpandableWidget;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class MyExpandableImageView extends AppCompatImageView implements ExpandableWidget {


    public MyExpandableImageView(Context context) {
        super(context);
    }

    public MyExpandableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyExpandableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isExpanded() {
        return false;
    }

    @Override
    public boolean setExpanded(boolean b) {
        return false;
    }
}
