package com.retrofit.rx2.cache.adapter.extend;

/**
 * ******************************
 *
 * @Author YOULU-ddc
 * date ：2020/10/26 0026
 * description:xxx
 * ******************************
 */
public enum Rx2CacheType {

    NO_CACHE,//无缓存
    DB_OR_NET,//本地缓存优先,本地错误走网络
    NET_OR_DB,//网络优先,网络错误走本地

}
