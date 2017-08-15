package com.xiongwo.huaweiappstore.mvp.view.view;


import com.xiongwo.huaweiappstore.base.mvpbase.BaseView;
import com.xiongwo.huaweiappstore.bean.AppCommentBean;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface AppCommentFragmentView extends BaseView {
    void onAppCommentDataSuccess(AppCommentBean appCommentBean);
    void onAppCommentDataError(String msg) ;
}
