package com.xiongwo.huaweiappstore.adapter.top;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.xiongwo.huaweiappstore.banner.RecommendController;
import com.xiongwo.recyclerviewlibrary.wrapper.HeaderAndFooterWrapper;

import java.util.List;

/**
 * Created by 熊，我 on 2017/8/12.
 */

public class RecommendTopWrapper extends HeaderAndFooterWrapper {

    private Context mContext;
    private RecommendController mRecommendController;

    public RecommendTopWrapper(Context context, RecyclerView.Adapter adapter) {
        super(adapter);
        mContext = context;
        mRecommendController = new RecommendController(mContext);
        addHeaderView(mRecommendController.getContentView());
    }

    public void addDataAll(List<String> list) {
        if (mRecommendController != null) {
            mRecommendController.setData(list);
        }
    }
}
