package com.xiongwo.huaweiappstore.mvp.presenter.impl;

import com.xiongwo.huaweiappstore.api.IGetDataDelegate;
import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.base.mvpbase.BasePresenterImpl;
import com.xiongwo.huaweiappstore.bean.CategoryBean;
import com.xiongwo.huaweiappstore.mvp.interactor.CategoryFragmentInteractor;
import com.xiongwo.huaweiappstore.mvp.presenter.CategoryFragmentPresenter;
import com.xiongwo.huaweiappstore.mvp.view.view.CategoryFragmentView;

import javax.inject.Inject;

/**
 * Created by 熊，我 on 2017/8/11.
 */

public class CategoryFragmentPresenterImpl extends BasePresenterImpl<CategoryFragmentView> implements CategoryFragmentPresenter{

    @Inject
    public CategoryFragmentInteractor mCategoryFragmentInteractor;

    @Inject
    public CategoryFragmentPresenterImpl() {

    }

    @Override
    public void getCategoryData(BaseActivity activity) {
        mCategoryFragmentInteractor.loadCategoryData(activity, new IGetDataDelegate<CategoryBean>() {
            @Override
            public void getDataSuccess(CategoryBean categoryBean) {
                mPresenterView.onCategoryDataSuccess(categoryBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onCategoryDataError(errmsg);
            }
        });
    }
}
