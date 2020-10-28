package com.retrofit.rx2.cache.adapter;

import android.util.Log;

import androidx.annotation.Nullable;

import com.retrofit.rx2.cache.adapter.extend.CacheCallExecuteObservable;
import com.retrofit.rx2.cache.adapter.extend.Rx2CacheConfig;
import com.retrofit.rx2.cache.adapter.extend.Rx2CacheType;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.Scheduler;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;

final class RxJava2CallAdapter<R> implements CallAdapter<R, Object> {
    private final Type responseType;
    private final @Nullable
    Scheduler scheduler;
    private final boolean isAsync;
    private final boolean isResult;
    private final boolean isBody;
    private final boolean isFlowable;
    private final boolean isSingle;
    private final boolean isMaybe;
    private final boolean isCompletable;

    private final Rx2CacheConfig rx2CacheConfig;

    RxJava2CallAdapter(Type responseType, @Nullable Scheduler scheduler, boolean isAsync,
                       boolean isResult, boolean isBody, boolean isFlowable, boolean isSingle, boolean isMaybe,
                       boolean isCompletable, Rx2CacheConfig rx2CacheConfig) {
        this.responseType = responseType;
        this.scheduler = scheduler;
        this.isAsync = isAsync;
        this.isResult = isResult;
        this.isBody = isBody;
        this.isFlowable = isFlowable;
        this.isSingle = isSingle;
        this.isMaybe = isMaybe;
        this.isCompletable = isCompletable;
        this.rx2CacheConfig = rx2CacheConfig;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public Object adapt(Call<R> call) {
        Observable<Response<R>> responseObservable;
        boolean isCache = rx2CacheConfig != null && rx2CacheConfig.isUseCache();
        if (isCache) {
            Log.d("ftd", "use cache");
            responseObservable = new CacheCallExecuteObservable<>(call);
        } else {
            responseObservable = isAsync
                    ? new CallEnqueueObservable<>(call)
                    : new CallExecuteObservable<>(call);
        }

        Observable<?> observable;
        if (isResult) {
            observable = new ResultObservable<>(responseObservable);
        } else if (isBody) {
            observable = new BodyObservable<>(responseObservable);
        } else {
            observable = responseObservable;
        }

        if (scheduler != null) {
            observable = observable.subscribeOn(scheduler);
        }

        if (isFlowable) {
            return observable.toFlowable(BackpressureStrategy.LATEST);
        }
        if (isSingle) {
            return observable.singleOrError();
        }
        if (isMaybe) {
            return observable.singleElement();
        }
        if (isCompletable) {
            return observable.ignoreElements();
        }
        return observable;
    }
}
