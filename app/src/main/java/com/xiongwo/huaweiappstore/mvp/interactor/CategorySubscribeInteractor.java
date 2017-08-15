package com.xiongwo.huaweiappstore.mvp.interactor;



import com.xiongwo.huaweiappstore.api.CategorySubcribeApi;
import com.xiongwo.huaweiappstore.api.IGetDataDelegate;
import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.bean.CategorySubscribeBean;
import com.xiongwo.huaweiappstore.util.JsonParserUtil;
import com.xiongwo.rxretrofitlibrary.http.HttpManager;
import com.xiongwo.rxretrofitlibrary.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class CategorySubscribeInteractor {

    private IGetDataDelegate<CategorySubscribeBean> mDelegate ;

    @Inject
    public CategorySubscribeInteractor(){

    }

    public void loadCategorySubscribeData(BaseActivity activity, IGetDataDelegate<CategorySubscribeBean> delegate){
        this.mDelegate = delegate ;
        CategorySubcribeApi categorySubcribeApi = new CategorySubcribeApi(httpListener,activity);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(categorySubcribeApi);
    }

    private HttpOnNextListener httpListener = new HttpOnNextListener<CategorySubscribeBean>(){

        @Override
        public void onNext(CategorySubscribeBean categorySubscribeBean) {
            mDelegate.getDataSuccess(categorySubscribeBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            CategorySubscribeBean categorySubscribeBean = JsonParserUtil.parseCategorySubscribeBean(string);
            mDelegate.getDataSuccess(categorySubscribeBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
