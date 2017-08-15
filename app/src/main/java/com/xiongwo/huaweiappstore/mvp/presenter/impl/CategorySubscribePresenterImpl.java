package com.xiongwo.huaweiappstore.mvp.presenter.impl;



import com.xiongwo.huaweiappstore.api.IGetDataDelegate;
import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.base.mvpbase.BasePresenterImpl;
import com.xiongwo.huaweiappstore.bean.CategorySubscribeBean;
import com.xiongwo.huaweiappstore.mvp.interactor.CategorySubscribeInteractor;
import com.xiongwo.huaweiappstore.mvp.presenter.CategorySubscribePresenter;
import com.xiongwo.huaweiappstore.mvp.view.view.CategorySubscribeView;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class CategorySubscribePresenterImpl extends BasePresenterImpl<CategorySubscribeView> implements CategorySubscribePresenter {

    @Inject
    public CategorySubscribeInteractor categorySubscribeInteractor ;

    @Inject
    public CategorySubscribePresenterImpl(){

    }

    @Override
    public void getCategorySubscribeData(BaseActivity activity) {
        categorySubscribeInteractor.loadCategorySubscribeData(activity, new IGetDataDelegate<CategorySubscribeBean>() {
            @Override
            public void getDataSuccess(CategorySubscribeBean categorySubscribeBean) {
                mPresenterView.onCategorySubscribeDataSuccess(categorySubscribeBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onCategorySubscribeDataError(errmsg);
            }
        });
    }
}
