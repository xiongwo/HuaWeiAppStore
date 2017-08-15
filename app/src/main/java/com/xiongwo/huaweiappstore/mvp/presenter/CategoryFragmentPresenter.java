package com.xiongwo.huaweiappstore.mvp.presenter;

import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.base.mvpbase.BasePresenter;
import com.xiongwo.huaweiappstore.mvp.view.view.CategoryFragmentView;

/**
 * Created by 熊，我 on 2017/8/11.
 */

public interface CategoryFragmentPresenter extends BasePresenter<CategoryFragmentView> {
    void getCategoryData(BaseActivity activity);
}
