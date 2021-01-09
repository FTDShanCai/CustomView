package com.example.customview.google.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.customview.R;
import com.example.customview.ui.fragment.EmptyFragment;

import java.util.ArrayList;


public class Fragment120Activity extends AppCompatActivity {
//    ViewPager view_pager;
//    ArrayList<Fragment> fragments = new ArrayList<>();

    FragmentContainerView fragment_container_view;

    Fragment fragment1;
    Fragment fragment2;
    Fragment fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ff_new);
//        view_pager = findViewById(R.id.view_pager);
//        init();

        fragment_container_view = findViewById(R.id.fragment_container_view);
        initFragment();
    }

//    private void init() {
//        fragments.add(EmptyFragment.newInstance("1"));
//        fragments.add(EmptyFragment.newInstance("2"));
//        fragments.add(EmptyFragment.newInstance("3"));
//
//        Adapter adapter = new Adapter(getSupportFragmentManager());
//        view_pager.setAdapter(adapter);
//    }


//    public class Adapter extends FragmentPagerAdapter {
//
//        @SuppressLint("WrongConstant")
//        public Adapter(@NonNull FragmentManager fm) {
//            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        }
//
//        @NonNull
//        @Override
//        public Fragment getItem(int position) {
//            return fragments.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return fragments.size();
//        }
//    }

    private void initFragment() {
        fragment1 = EmptyFragment.newInstance("1");
        fragment2 = EmptyFragment.newInstance("2");
        fragment3 = EmptyFragment.newInstance("3");


    }

    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(), fragment1, R.id.fragment_container_view, "1");
                break;
            case R.id.btn2:
                ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(), fragment2, R.id.fragment_container_view, "2");
                break;
            case R.id.btn3:
                ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(), fragment3, R.id.fragment_container_view, "3");
                break;
        }
    }
}