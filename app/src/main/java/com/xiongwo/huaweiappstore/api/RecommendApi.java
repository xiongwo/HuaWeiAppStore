package com.xiongwo.huaweiappstore.api;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.xiongwo.huaweiappstore.bean.RecommendBean;
import com.xiongwo.huaweiappstore.util.JsonParserUtil;
import com.xiongwo.rxretrofitlibrary.api.BaseApi;
import com.xiongwo.rxretrofitlibrary.listener.HttpOnNextListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by 熊，我 on 2017/8/10.
 */

public class RecommendApi extends BaseApi<RecommendBean> {

    public RecommendApi(HttpOnNextListener httpOnNextListener, RxAppCompatActivity activity) {
        super(httpOnNextListener, activity);
        setMothed("AppStore/recommend");
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getRecommendData();
    }

    @Override
    public RecommendBean call(ResponseBody responseBody) {
        String result = "";
        try {
            result = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonParserUtil.parseRecommendBean(result);
    }
}
