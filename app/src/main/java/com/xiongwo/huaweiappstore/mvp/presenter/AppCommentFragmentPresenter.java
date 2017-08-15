package com.xiongwo.huaweiappstore.mvp.presenter;


import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.base.mvpbase.BasePresenter;
import com.xiongwo.huaweiappstore.mvp.view.view.AppCommentFragmentView;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface AppCommentFragmentPresenter extends BasePresenter<AppCommentFragmentView> {
    void getAppCommentData(BaseActivity activity, String packageName);
}
