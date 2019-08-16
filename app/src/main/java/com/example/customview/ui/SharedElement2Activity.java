package com.example.customview.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.SharedElementCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.customview.R;
import com.example.customview.ui.fragment.ImageFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SharedElement2Activity extends AppCompatActivity {
    ViewPager view_pager;
    MyPageAdapter myPageAdapter;
    int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element2);
        view_pager = findViewById(R.id.view_pager);
        initData();
        supportPostponeEnterTransition();
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (String url : urls) {
            ImageFragment imageFragment = new ImageFragment(url);
            fragments.add(imageFragment);
        }

        myPageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
        view_pager.setAdapter(myPageAdapter);
        view_pager.setCurrentItem(current);
        view_pager.setOffscreenPageLimit(urls.size() - 1);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Fade fade = new Fade();
//            fade.setDuration(500);
//            getWindow().setEnterTransition(fade);
//            getWindow().setExitTransition(fade);
//
//            TransitionSet transitionSet = new TransitionSet();
//            transitionSet.setDuration(500);
//            ChangeBounds changeBounds = new ChangeBounds();
//            transitionSet.addTransition(changeBounds);
//            ChangeTransform changeTransform = new ChangeTransform();
//            ChangeImageTransform changeImageTransform = new ChangeImageTransform();
//            transitionSet.addTransition(changeImageTransform);
//            transitionSet.addTransition(changeTransform);
//            getWindow().setSharedElementEnterTransition(transitionSet);
//            getWindow().setSharedElementExitTransition(transitionSet);
        }

        setEnterSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                String url = urls.get(view_pager.getCurrentItem());
                ImageFragment imageFragment = (ImageFragment) myPageAdapter.instantiateItem(view_pager, view_pager.getCurrentItem());
                names.clear();
                names.add(url);
                sharedElements.clear();
                sharedElements.put(url, imageFragment.getImageView());
            }
        });

        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                HashMap<String, Integer> map = new HashMap<>();
                map.put("index", position);
                EventBus.getDefault().post(map);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private ArrayList<String> urls = new ArrayList<>();

    private void initData() {
        Intent intent = getIntent();
        urls.addAll(intent.getStringArrayListExtra("urls"));
        current = intent.getIntExtra("current", 0);
    }

    class MyPageAdapter extends FragmentStatePagerAdapter {

        private ArrayList<Fragment> fragments;

        public MyPageAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    @Override
    public void onBackPressed() {
        this.supportFinishAfterTransition();
    }

    @Override
    public void supportFinishAfterTransition() {
        Intent data = new Intent();
        data.putExtra("index", view_pager.getCurrentItem());
        setResult(RESULT_OK, data);
        super.supportFinishAfterTransition();
    }
}
