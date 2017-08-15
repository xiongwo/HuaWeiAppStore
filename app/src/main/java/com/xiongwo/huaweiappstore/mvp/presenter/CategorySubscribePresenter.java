package com.xiongwo.huaweiappstore.mvp.presenter;


import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.base.mvpbase.BasePresenter;
import com.xiongwo.huaweiappstore.mvp.view.view.CategorySubscribeView;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface CategorySubscribePresenter extends BasePresenter<CategorySubscribeView> {
    void getCategorySubscribeData(BaseActivity activity);
}
