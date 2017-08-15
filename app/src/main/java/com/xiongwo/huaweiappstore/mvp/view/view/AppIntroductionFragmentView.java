package com.xiongwo.huaweiappstore.mvp.view.view;


import com.xiongwo.huaweiappstore.base.mvpbase.BaseView;
import com.xiongwo.huaweiappstore.bean.AppIntroductionBean;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface AppIntroductionFragmentView extends BaseView {
    void onAppIntroductionDataSuccess(AppIntroductionBean appIntroductionBean);
    void onAppIntroductionDataError(String msg) ;
}
