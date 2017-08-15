package com.xiongwo.huaweiappstore.mvp.presenter.impl;

import com.xiongwo.huaweiappstore.api.IGetDataDelegate;
import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.base.mvpbase.BasePresenterImpl;
import com.xiongwo.huaweiappstore.bean.AppDetailBean;
import com.xiongwo.huaweiappstore.mvp.interactor.AppDetailActivityInteractor;
import com.xiongwo.huaweiappstore.mvp.presenter.AppDetailActivityPresenter;
import com.xiongwo.huaweiappstore.mvp.view.view.AppDetailActivityView;

import javax.inject.Inject;


/**
 * Created by 熊，我 on 2017/8/14.
 */

public class AppDetailActivityPresenterImpl extends BasePresenterImpl<AppDetailActivityView> implements AppDetailActivityPresenter {

    @Inject
    public AppDetailActivityInteractor mInteractor;

    @Inject
    public AppDetailActivityPresenterImpl() {

    }

    @Override
    public void getAppDetailData(BaseActivity baseActivity, String packageName) {
        mInteractor.loadAppDetailData(baseActivity, packageName, new IGetDataDelegate<AppDetailBean>() {
            @Override
            public void getDataSuccess(AppDetailBean appDetailBean) {
                mPresenterView.onAppDetailDataSuccess(appDetailBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onAppDetailDataError(errmsg);
            }
        });
    }
}
