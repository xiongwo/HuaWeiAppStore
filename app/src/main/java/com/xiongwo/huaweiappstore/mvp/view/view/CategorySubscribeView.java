package com.xiongwo.huaweiappstore.mvp.view.view;


import com.xiongwo.huaweiappstore.base.mvpbase.BaseView;
import com.xiongwo.huaweiappstore.bean.CategorySubscribeBean;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface CategorySubscribeView extends BaseView {
    void onCategorySubscribeDataSuccess(CategorySubscribeBean categorySubscribeBean) ;
    void onCategorySubscribeDataError(String msg);
}
