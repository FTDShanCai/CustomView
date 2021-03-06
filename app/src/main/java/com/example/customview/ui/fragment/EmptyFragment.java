package com.example.customview.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.customview.google.fragment.BaseLazyFragment;

import java.util.Random;

/**
 * ******************************
 *
 * @Author YOULU-ddc
 * date ：2020/7/30 0030
 * description:xxx
 * ******************************
 */
public class EmptyFragment extends BaseLazyFragment {

    String tag = "";

    public static EmptyFragment newInstance(String text) {
        Bundle args = new Bundle();
        args.putString("text", text);
        EmptyFragment fragment = new EmptyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Random random = new Random();
        int color = Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));
        FrameLayout frameLayout = new FrameLayout(requireContext());
        frameLayout.setBackgroundColor(color);
        TextView textView = new TextView(requireContext());
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        tag = getArguments().getString("text");
        textView.setText(getArguments() == null ? "null" : getArguments().getString("text"));
        frameLayout.addView(textView);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = -1;
        layoutParams.height = -1;
        return frameLayout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ftd", tag + "  onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("ftd", tag + "  onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("ftd", tag + "  onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("ftd", tag + "  onPause");
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        Log.d("ftd", tag + "     lazyLoad");
    }
}
