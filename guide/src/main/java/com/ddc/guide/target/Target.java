package com.ddc.guide.target;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

public interface Target {
    Rect getRect();

    View getView();

    Point getPoint();
}
