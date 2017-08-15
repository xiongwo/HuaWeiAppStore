package com.xiongwo.huaweiappstore.base.mvpbase;

/**
 * MVP  Presenter实现类的基类
 * Created by 熊，我 on 2017/8/7.
 */

public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {

    protected T mPresenterView;

    @Override
    public void attachView(T view) {
        mPresenterView = view;
    }

    @Override
    public void detachView() {
        mPresenterView = null;
    }
}
