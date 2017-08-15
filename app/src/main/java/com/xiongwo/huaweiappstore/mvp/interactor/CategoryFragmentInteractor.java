package com.xiongwo.huaweiappstore.mvp.interactor;

import com.xiongwo.huaweiappstore.api.CategoryApi;
import com.xiongwo.huaweiappstore.api.IGetDataDelegate;
import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.bean.CategoryBean;
import com.xiongwo.huaweiappstore.util.JsonParserUtil;
import com.xiongwo.rxretrofitlibrary.http.HttpManager;
import com.xiongwo.rxretrofitlibrary.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Created by 熊，我 on 2017/8/11.
 */

public class CategoryFragmentInteractor {

    private IGetDataDelegate<CategoryBean> mDelegate;

    @Inject
    public CategoryFragmentInteractor() {

    }

    private HttpOnNextListener<CategoryBean> mHttpOnNextListener = new HttpOnNextListener<CategoryBean>() {
        @Override
        public void onNext(CategoryBean categoryBean) {
            mDelegate.getDataSuccess(categoryBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            CategoryBean categoryBean = JsonParserUtil.parseCategoryBean(string);
            mDelegate.getDataSuccess(categoryBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };

    public void loadCategoryData(BaseActivity baseActivity, IGetDataDelegate<CategoryBean> delegate) {
        mDelegate = delegate;
        CategoryApi categoryApi = new CategoryApi(mHttpOnNextListener, baseActivity);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(categoryApi);
    }
}
