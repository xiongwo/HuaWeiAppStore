package com.xiongwo.huaweiappstore.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.xiongwo.huaweiappstore.base.mvpbase.BasePresenter;
import com.xiongwo.huaweiappstore.base.mvpbase.BaseView;

import com.xiongwo.huaweiappstore.di.component.DaggerFragmentComponent;
import com.xiongwo.huaweiappstore.di.component.FragmentComponent;
import com.xiongwo.huaweiappstore.di.module.FragmentModule;

/**
 * MVP  Fragment的基类
 * 继承BaseFragment，初始化Dagger2中的FragmentComponent，抽象完成依赖注入的方法
 * Created by 熊，我 on 2017/8/7.
 */

public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {

    protected FragmentComponent mFragmentComponent;
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initFragmentComponent();
        mPresenter = initInject();
        mPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    private void initFragmentComponent() {
        mFragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(((StoreApplication) getActivity().getApplication()).getApplicationComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }

    /**
     * 完成注入，并返回Presenter对象
     * @return
     */
    protected abstract T initInject();
}
