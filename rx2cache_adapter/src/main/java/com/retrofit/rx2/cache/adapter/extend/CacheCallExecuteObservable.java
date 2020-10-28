package com.retrofit.rx2.cache.adapter.extend;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Call;
import retrofit2.Response;

/**
 * ******************************
 *
 * @Author YOULU-ddc
 * date ：2020/10/27 0027
 * description:xxx
 * ******************************
 */
public final class CacheCallExecuteObservable<T> extends Observable<Response<T>> {

    private final Call<T> originalCall;

    public CacheCallExecuteObservable(Call<T> originalCall) {
        this.originalCall = originalCall;
    }

    @Override
    protected void subscribeActual(Observer<? super Response<T>> observer) {
        Call<T> call = originalCall.clone();
        CacheDisposable disposable = new CacheDisposable(call);
        observer.onSubscribe(disposable);

        boolean isCacheOver = false;
        try {
            T cache = CacheHelper.getCache("xxxx", "xxxx");
            if (cache != null) {
                if (!disposable.isDisposed()) {
                    observer.onNext(Response.success(cache));
                }

                if (!disposable.isDisposed()) {
                    isCacheOver = true;
                    observer.onComplete();
                }
            }
        } catch (Throwable t) {
            Exceptions.throwIfFatal(t);
            CacheHelper.clearCache("xxxx", "xxxx");
            //TODO  DB CLOSE
            if (isCacheOver) {
                RxJavaPlugins.onError(t);
            }
        }

        if (isCacheOver) return;//缓存可用

        boolean terminated = false;
        try {
            Response<T> response = call.execute();
            if (!disposable.isDisposed()) {
                observer.onNext(response);
            }
            if (!disposable.isDisposed()) {
                terminated = true;
                observer.onComplete();
            }
        } catch (Throwable t) {
            Exceptions.throwIfFatal(t);
            if (terminated) {
                RxJavaPlugins.onError(t);
            } else if (!disposable.isDisposed()) {
                try {
                    observer.onError(t);
                } catch (Throwable inner) {
                    Exceptions.throwIfFatal(inner);
                    RxJavaPlugins.onError(new CompositeException(t, inner));
                }
            }
        }

    }


    private static class CacheDisposable implements Disposable {
        private volatile boolean disposed;
        private final Call<?> call;

        public CacheDisposable(Call<?> call) {
            this.call = call;
        }

        @Override
        public void dispose() {
            disposed = true;
            call.cancel();
        }

        @Override
        public boolean isDisposed() {
            return disposed;
        }
    }
}
