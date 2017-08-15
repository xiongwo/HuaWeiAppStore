package com.xiongwo.huaweiappstore.mvp.view.view;

import com.xiongwo.huaweiappstore.base.mvpbase.BaseView;
import com.xiongwo.huaweiappstore.bean.TopBean;

/**
 * Created by 熊，我 on 2017/8/11.
 */

public interface TopFragmentView extends BaseView {
    void onTopDataSuccess(TopBean topBean);
    void onTopDataError(String errorMsg);
}
