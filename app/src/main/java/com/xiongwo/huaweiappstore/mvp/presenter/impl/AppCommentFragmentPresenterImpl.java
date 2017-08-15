package com.xiongwo.huaweiappstore.mvp.presenter.impl;



import com.xiongwo.huaweiappstore.api.IGetDataDelegate;
import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.base.mvpbase.BasePresenterImpl;
import com.xiongwo.huaweiappstore.bean.AppCommentBean;
import com.xiongwo.huaweiappstore.mvp.interactor.AppCommentFragmentInteractor;
import com.xiongwo.huaweiappstore.mvp.presenter.AppCommentFragmentPresenter;
import com.xiongwo.huaweiappstore.mvp.view.view.AppCommentFragmentView;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppCommentFragmentPresenterImpl extends BasePresenterImpl<AppCommentFragmentView> implements AppCommentFragmentPresenter {

    @Inject
    AppCommentFragmentInteractor appCommentFragmentInteractor ;

    @Inject
    public AppCommentFragmentPresenterImpl(){

    }

    @Override
    public void getAppCommentData(BaseActivity activity, String packageName) {
        appCommentFragmentInteractor.loadAppCommentData(activity, packageName, new IGetDataDelegate<AppCommentBean>() {
            @Override
            public void getDataSuccess(AppCommentBean appCommentBean) {
                mPresenterView.onAppCommentDataSuccess(appCommentBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onAppCommentDataError(errmsg);
            }
        });
    }
}
