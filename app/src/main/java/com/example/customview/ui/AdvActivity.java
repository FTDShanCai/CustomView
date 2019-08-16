package com.example.customview.ui;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.icu.util.ValueIterator;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.transition.ChangeBounds;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.TransitionSet;
import android.transition.TransitionValues;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.SharedElementCallback;

import com.example.customview.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Map;

public class AdvActivity extends AppCompatActivity {
    ConstraintLayout root_view;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adv);
        root_view = findViewById(R.id.root_view);
        fab = findViewById(R.id.fab);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade fade = new Fade();
            fade.setDuration(2300);
            getWindow().setEnterTransition(fade);
            getWindow().setExitTransition(fade);

            TransitionSet transitionSet = new TransitionSet();
            transitionSet.setDuration(800);
            ChangeBounds changeBounds = new ChangeBounds();
            transitionSet.addTransition(changeBounds);
            ChangeTransform changeTransform = new ChangeTransform();
            transitionSet.addTransition(changeTransform);
            transitionSet.addTarget(fab);
            getWindow().setSharedElementEnterTransition(transitionSet);
            getWindow().setSharedElementExitTransition(transitionSet);

            MySharedElement enterSharedElement = new MySharedElement("enter");
            MySharedElement exitSharedElement = new MySharedElement("exit");
            setEnterSharedElementCallback(enterSharedElement);
            setExitSharedElementCallback(exitSharedElement);
        }
    }

    public class MySharedElement extends SharedElementCallback {
        private String tag = "ftd";
        private String log;

        public MySharedElement(String log) {
            super();
            log = log + "   ";
        }

        @Override
        public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
            super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots);
            Log.d(tag, log + "onSharedElementStart");
        }

        @Override
        public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
            super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots);
            Log.d(tag, log + "onSharedElementEnd");
        }

        @Override
        public void onRejectSharedElements(List<View> rejectedSharedElements) {
            super.onRejectSharedElements(rejectedSharedElements);
            Log.d(tag, log + "onRejectSharedElements");
        }

        @Override
        public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
            super.onMapSharedElements(names, sharedElements);
            Log.d(tag, log + "onMapSharedElements");
        }

        @Override
        public Parcelable onCaptureSharedElementSnapshot(View sharedElement, Matrix viewToGlobalMatrix, RectF screenBounds) {
            Log.d(tag, log + "onCaptureSharedElementSnapshot");
            return super.onCaptureSharedElementSnapshot(sharedElement, viewToGlobalMatrix, screenBounds);
        }

        @Override
        public View onCreateSnapshotView(Context context, Parcelable snapshot) {
            Log.d(tag, log + "onCreateSnapshotView");
            return super.onCreateSnapshotView(context, snapshot);
        }

        @Override
        public void onSharedElementsArrived(List<String> sharedElementNames, List<View> sharedElements, OnSharedElementsReadyListener listener) {
            super.onSharedElementsArrived(sharedElementNames, sharedElements, listener);
            Log.d(tag, log + "onSharedElementsArrived");
        }
    }


}
