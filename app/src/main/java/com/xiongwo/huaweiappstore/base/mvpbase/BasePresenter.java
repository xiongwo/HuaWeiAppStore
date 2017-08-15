package com.xiongwo.huaweiappstore.base.mvpbase;

/**
 * MVP  Presenter的基类
 * 提供绑定与解绑View的方法，View是继承BaseView的泛型
 * Created by 熊，我 on 2017/8/7.
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);
    void detachView();
}
