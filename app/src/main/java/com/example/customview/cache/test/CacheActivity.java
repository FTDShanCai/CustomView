package com.example.customview.cache.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.customview.R;
import com.example.customview.jetpack.wan.api.ApiFactory;
import com.example.customview.jetpack.wan.api.WanApi;
import com.example.customview.jetpack.wan.bean.ArticleBean;
import com.example.customview.jetpack.wan.bean.WanBaseResponse;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CacheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);
        findViewById(R.id.btn).setOnClickListener(v -> test());
    }

    private void test() {
        ApiFactory.Companion.getInstance().create(WanApi.class).getArticle(1).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WanBaseResponse<List<ArticleBean>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull WanBaseResponse<List<ArticleBean>> listWanBaseResponse) {
                        Log.d("ftd", "onNext");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("ftd", "onError");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}