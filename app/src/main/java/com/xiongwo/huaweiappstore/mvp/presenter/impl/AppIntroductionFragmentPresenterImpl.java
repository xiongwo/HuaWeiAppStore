package com.xiongwo.huaweiappstore.mvp.presenter.impl;



import com.xiongwo.huaweiappstore.api.IGetDataDelegate;
import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.base.mvpbase.BasePresenterImpl;
import com.xiongwo.huaweiappstore.bean.AppIntroductionBean;
import com.xiongwo.huaweiappstore.mvp.interactor.AppIntroductionIntroductor;
import com.xiongwo.huaweiappstore.mvp.presenter.AppIntroductionFragmentPresenter;
import com.xiongwo.huaweiappstore.mvp.view.view.AppIntroductionFragmentView;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppIntroductionFragmentPresenterImpl extends BasePresenterImpl<AppIntroductionFragmentView> implements AppIntroductionFragmentPresenter {

    @Inject
    AppIntroductionIntroductor appIntroductionIntroductor ;

    @Inject
    public AppIntroductionFragmentPresenterImpl(){

    }

    @Override
    public void getAppIntroductionData(BaseActivity activity, String packageName) {
        appIntroductionIntroductor.loadAppIntroductino(activity, packageName, new IGetDataDelegate<AppIntroductionBean>() {
            @Override
            public void getDataSuccess(AppIntroductionBean appIntroductionBean) {
                mPresenterView.onAppIntroductionDataSuccess(appIntroductionBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onAppIntroductionDataError(errmsg);
            }
        });
    }
}
