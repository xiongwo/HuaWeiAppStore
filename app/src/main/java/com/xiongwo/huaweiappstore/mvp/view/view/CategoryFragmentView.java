package com.xiongwo.huaweiappstore.mvp.view.view;

import com.xiongwo.huaweiappstore.base.mvpbase.BaseView;
import com.xiongwo.huaweiappstore.bean.CategoryBean;

/**
 * Created by 熊，我 on 2017/8/11.
 */

public interface CategoryFragmentView extends BaseView {
    void onCategoryDataSuccess(CategoryBean categoryBean);
    void onCategoryDataError(String msg);
}
