package com.xiongwo.huaweiappstore.mvp.presenter.impl;

import com.xiongwo.huaweiappstore.api.IGetDataDelegate;
import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.base.mvpbase.BasePresenterImpl;
import com.xiongwo.huaweiappstore.bean.TopBean;
import com.xiongwo.huaweiappstore.mvp.interactor.TopFragmentInteractor;
import com.xiongwo.huaweiappstore.mvp.presenter.TopFragmentPresenter;
import com.xiongwo.huaweiappstore.mvp.view.view.TopFragmentView;

import javax.inject.Inject;

/**
 * Created by 熊，我 on 2017/8/11.
 */

public class TopFragmentPresenterImpl extends BasePresenterImpl<TopFragmentView> implements TopFragmentPresenter {

    @Inject
    public TopFragmentInteractor mTopFragmentInteractor;

    @Inject
    public TopFragmentPresenterImpl() {

    }

    @Override
    public void getTopData(BaseActivity baseActivity) {
        mTopFragmentInteractor.loadTopData(baseActivity, new IGetDataDelegate<TopBean>() {
            @Override
            public void getDataSuccess(TopBean topBean) {
                mPresenterView.onTopDataSuccess(topBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onTopDataError(errmsg);
            }
        });
    }
}
