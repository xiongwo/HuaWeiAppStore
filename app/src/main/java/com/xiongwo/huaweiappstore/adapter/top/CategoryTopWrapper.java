package com.xiongwo.huaweiappstore.adapter.top;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.xiongwo.huaweiappstore.R;
import com.xiongwo.huaweiappstore.bean.CategoryBean;
import com.xiongwo.huaweiappstore.util.LogUtil;
import com.xiongwo.huaweiappstore.util.UIUtil;
import com.xiongwo.recyclerviewlibrary.wrapper.HeaderAndFooterWrapper;

import java.util.List;

/**
 * Created by 熊，我 on 2017/8/12.
 */

public class CategoryTopWrapper extends HeaderAndFooterWrapper {

    private Context mContext;
    private View mHeadView;
    private GridView mGridView;
    private List<CategoryBean.CategoryTopBean> mTopBeanList;
    private OnItemClickListener mOnItemClickListener;

    public CategoryTopWrapper(Context context, RecyclerView.Adapter adapter) {
        super(adapter);
        mContext = context;
        mHeadView = UIUtil.inflate(R.layout.header_top);
        mGridView = (GridView) mHeadView.findViewById(R.id.gv_title_grid);
        addHeaderView(mHeadView);
    }

    public void addTopData(List<CategoryBean.CategoryTopBean> topBeanList) {
        mTopBeanList = topBeanList;

        CategorySubAdapter subAdapter = new CategorySubAdapter(mContext, mTopBeanList);
        LogUtil.information("size --> " + mTopBeanList.size());
        mGridView.setNumColumns(mTopBeanList.size());
        mGridView.setAdapter(subAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position);
                }
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }
}
