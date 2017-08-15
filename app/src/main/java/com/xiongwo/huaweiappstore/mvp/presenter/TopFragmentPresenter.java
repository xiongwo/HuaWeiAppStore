package com.xiongwo.huaweiappstore.mvp.presenter;

import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.base.mvpbase.BasePresenter;
import com.xiongwo.huaweiappstore.mvp.view.view.TopFragmentView;

/**
 * Created by 熊，我 on 2017/8/11.
 */

public interface TopFragmentPresenter extends BasePresenter<TopFragmentView> {
    void getTopData(BaseActivity baseActivity);
}
