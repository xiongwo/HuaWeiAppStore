package com.xiongwo.huaweiappstore.mvp.presenter.impl;


import com.xiongwo.huaweiappstore.api.IGetDataDelegate;
import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.base.mvpbase.BasePresenterImpl;
import com.xiongwo.huaweiappstore.bean.AppMoreRecommendBean;
import com.xiongwo.huaweiappstore.mvp.interactor.AppMoreRecommendInteractor;
import com.xiongwo.huaweiappstore.mvp.presenter.AppMoreRecommendPresenter;
import com.xiongwo.huaweiappstore.mvp.view.view.AppMoreRecommendView;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppMoreRecommendPresenterImpl extends BasePresenterImpl<AppMoreRecommendView> implements AppMoreRecommendPresenter {

    @Inject
    public AppMoreRecommendInteractor appMoreRecommendInteractor ;

    @Inject
    AppMoreRecommendPresenterImpl(){

    }

    @Override
    public void getAppMoreRecommendData(BaseActivity activity, String type, String packageName) {
        appMoreRecommendInteractor.loadAppMoreRecommend(activity, type, packageName, new IGetDataDelegate<AppMoreRecommendBean>() {
            @Override
            public void getDataSuccess(AppMoreRecommendBean appMoreRecommendBean) {
                mPresenterView.onAppMoreRecommendDataSuccess(appMoreRecommendBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onAppMoreRecommendDataError(errmsg);
            }
        });
    }
}
