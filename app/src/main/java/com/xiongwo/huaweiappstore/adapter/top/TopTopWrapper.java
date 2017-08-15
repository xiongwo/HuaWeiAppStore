package com.xiongwo.huaweiappstore.adapter.top;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;

import com.xiongwo.huaweiappstore.R;
import com.xiongwo.huaweiappstore.bean.CategoryBean;
import com.xiongwo.huaweiappstore.bean.TopBean;
import com.xiongwo.huaweiappstore.util.LogUtil;
import com.xiongwo.huaweiappstore.util.UIUtil;
import com.xiongwo.recyclerviewlibrary.wrapper.HeaderAndFooterWrapper;

import java.util.List;

/**
 * Created by 熊，我 on 2017/8/12.
 */

public class TopTopWrapper extends HeaderAndFooterWrapper {

    private Context mContext;
    private View mHeadView;
    private GridView mGridView;
    private List<TopBean.TopTopBean> mTopBeanList;

    public TopTopWrapper(Context context, RecyclerView.Adapter adapter) {
        super(adapter);
        mContext = context;
        mHeadView = UIUtil.inflate(R.layout.header_top);
        mGridView = (GridView) mHeadView.findViewById(R.id.gv_title_grid);
        addHeaderView(mHeadView);
    }

    public void addTopData(List<TopBean.TopTopBean> topBeanList) {
        mTopBeanList = topBeanList;

        TopSubAdapter topSubAdapter = new TopSubAdapter(mContext, mTopBeanList);
        mGridView.setNumColumns(mTopBeanList.size());
        mGridView.setAdapter(topSubAdapter);
    }
}
