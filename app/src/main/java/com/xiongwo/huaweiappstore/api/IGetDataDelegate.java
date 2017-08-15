package com.xiongwo.huaweiappstore.api;

/**
 *
 * 网络请求回调
 *
 * Created by xzhang on 2017/5/22.
 */

public interface IGetDataDelegate<T> {
    void getDataSuccess(T t);
    void getDataError(String errmsg);
}
