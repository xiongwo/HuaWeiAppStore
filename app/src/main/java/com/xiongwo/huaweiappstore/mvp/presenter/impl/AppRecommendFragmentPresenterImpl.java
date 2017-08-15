package com.xiongwo.huaweiappstore.mvp.presenter.impl;



import com.xiongwo.huaweiappstore.api.IGetDataDelegate;
import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.base.mvpbase.BasePresenterImpl;
import com.xiongwo.huaweiappstore.bean.AppRecommendBean;
import com.xiongwo.huaweiappstore.mvp.presenter.AppRecommendFragmentPresenter;
import com.xiongwo.huaweiappstore.mvp.view.view.AppRecommendFragmentView;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppRecommendFragmentPresenterImpl extends BasePresenterImpl<AppRecommendFragmentView> implements AppRecommendFragmentPresenter {

    @Inject
    public AppRecommendInteractor appRecommendInteractor ;

    @Inject
    public AppRecommendFragmentPresenterImpl(){

    }

    @Override
    public void getAppRecommendData(BaseActivity activity, String packageName) {
        appRecommendInteractor.loadAppRecommend(activity, packageName, new IGetDataDelegate<AppRecommendBean>() {
            @Override
            public void getDataSuccess(AppRecommendBean appRecommendBean) {
                mPresenterView.onAppRecommendDataSuccess(appRecommendBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onAppRecommendDataError(errmsg);
            }
        });
    }
}
