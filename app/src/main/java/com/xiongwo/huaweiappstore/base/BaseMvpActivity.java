package com.xiongwo.huaweiappstore.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.xiongwo.huaweiappstore.base.mvpbase.BasePresenter;
import com.xiongwo.huaweiappstore.base.mvpbase.BaseView;
import com.xiongwo.huaweiappstore.di.component.ActivityComponent;
import com.xiongwo.huaweiappstore.di.component.DaggerActivityComponent;
import com.xiongwo.huaweiappstore.di.module.ActivityModule;


/**
 * MVP  Activity的基类
 * 继承BaseActivity，初始化Dagger2中的ActivityComponent，抽象完成依赖注入的方法
 * Created by 熊，我 on 2017/8/7.
 */

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    protected ActivityComponent mActivityComponent;
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initActivityComponent();
        mPresenter = initInject();
        mPresenter.attachView(this);
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void initActivityComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((StoreApplication) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 完成注入，并返回Presenter对象
     * @return
     */
    protected abstract T initInject();
}
