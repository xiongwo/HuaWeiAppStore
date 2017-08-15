package com.xiongwo.huaweiappstore.mvp.interactor;

import com.xiongwo.huaweiappstore.api.IGetDataDelegate;
import com.xiongwo.huaweiappstore.api.TopApi;
import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.bean.TopBean;
import com.xiongwo.huaweiappstore.util.JsonParserUtil;
import com.xiongwo.rxretrofitlibrary.http.HttpManager;
import com.xiongwo.rxretrofitlibrary.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Created by 熊，我 on 2017/8/11.
 */

public class TopFragmentInteractor {

    private IGetDataDelegate<TopBean> mDelegate;

    @Inject
    public TopFragmentInteractor() {

    }

    private HttpOnNextListener<TopBean> mListener = new HttpOnNextListener<TopBean>() {
        @Override
        public void onNext(TopBean topBean) {
            mDelegate.getDataSuccess(topBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            TopBean topBean = JsonParserUtil.parseTopBean(string);
            mDelegate.getDataSuccess(topBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };

    public void loadTopData(BaseActivity baseActivity, IGetDataDelegate<TopBean> delegate) {
        mDelegate = delegate;
        TopApi topApi = new TopApi(mListener, baseActivity);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(topApi);
    }
}
