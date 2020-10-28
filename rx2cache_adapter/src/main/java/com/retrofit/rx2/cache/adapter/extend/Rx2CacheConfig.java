package com.retrofit.rx2.cache.adapter.extend;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.lang.reflect.Type;

/**
 * ******************************
 *
 * @Author YOULU-ddc
 * date ：2020/10/26 0026
 * description:xxx
 * ******************************
 */
public class Rx2CacheConfig {

    public Rx2CacheConfig(Builder builder) {
        this.cacheType = builder.cacheType;
        this.responseType = builder.responseType;
    }

    @NonNull
    private Rx2CacheType cacheType;

    private Type responseType;

    public @NonNull
    Rx2CacheType getCacheType() {
        return cacheType;
    }

    /**
     * 是否使用缓存策略
     *
     * @return
     */
    public boolean isUseCache() {
        return cacheType != Rx2CacheType.NO_CACHE && responseType != null;
    }

    public static class Builder {
        private Rx2CacheType cacheType;
        private Type responseType;

        public Builder setCacheType(Rx2CacheType cacheType) {
            this.cacheType = cacheType;
            return this;
        }

        public Builder setResponseType(Type responseType) {
            this.responseType = responseType;
            return this;
        }

        public Rx2CacheConfig build() {

            if (cacheType == null) {
                cacheType = Rx2CacheType.NO_CACHE;
            }

            return new Rx2CacheConfig(this);
        }
    }

}
