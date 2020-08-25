package com.example.customview.rx;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customview.R;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class RxActivity extends AppCompatActivity {
    private Button btn_retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        btn_retry = findViewById(R.id.btn_retry);

        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    private AtomicBoolean mRefreshing = new AtomicBoolean(false);
    private Observable<String> stringObservable = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObservableEmitter<String> emitter) throws Exception {
            Log.d("ftd", "获取token");
            Thread.sleep(1000);
            emitter.onNext(Long.toString(System.currentTimeMillis()));
            emitter.onComplete();
        }
    }).doOnNext(new Consumer<String>() {
        @Override
        public void accept(String s) {
            MToken.getInstance().setToken(s);
            mRefreshing.set(false);
        }
    }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    private Observable<String> refreshToken() {
        PublishSubject<String> publishSubject = PublishSubject.create();
//        if (mRefreshing.compareAndSet(false, true)) {
        Log.d("ftd", "没有请求，发起一次新的Token请求");
        getToken(publishSubject);
//        } else {
//            Log.d("ftd", "已经有请求，直接返回等待");
//        }
        return publishSubject;
    }

    private void getToken(PublishSubject<String> publishSubject) {
        stringObservable.subscribe(publishSubject);
    }

    private void getData() {
        Observable.defer(new Callable<ObservableSource<String>>() {
            @Override
            public ObservableSource<String> call() {
                return Observable.just(MToken.getInstance().getToken());
            }
        }).flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) {
                return getUserInfo(s, "ddc");
            }
        }).retryWhen(new Function<Observable<Throwable>, ObservableSource<String>>() {

            @Override
            public ObservableSource<String> apply(Observable<Throwable> throwableObservable) {
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Throwable throwable) {
                        Log.d("ftd", "retryWhen:" + throwable);
                        return refreshToken();
                    }
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d("ftd", s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("ftd", "onError:" + e);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private Observable<String> getUserInfo(final String token, final String account) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.d("ftd", "getUserInfo:" + token + "   account:" + account);
                Thread.sleep(2000);
                if (token.equals("")) {
                    Log.d("ftd", "token is null");
                    emitter.onError(new Throwable("token 不正确"));
                } else if (System.currentTimeMillis() - Long.parseLong(token) > 10 * 1000) {
                    Log.d("ftd", "token is 过期");
                    emitter.onError(new Throwable("token 不正确"));
                } else {
                    Log.d("ftd", "token is 正确");
                    emitter.onNext("正确");
                    emitter.onComplete();
                }
            }
        });
    }


}