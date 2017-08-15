package com.xiongwo.huaweiappstore.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xiongwo.huaweiappstore.R;
import com.xiongwo.huaweiappstore.bean.AppBean;
import com.xiongwo.huaweiappstore.bean.RecommendBean;
import com.xiongwo.recyclerviewlibrary.adapter.CommonAdapter;
import com.xiongwo.recyclerviewlibrary.adapter.MultiItemTypeAdapter;
import com.xiongwo.recyclerviewlibrary.base.ItemViewDelegate;
import com.xiongwo.recyclerviewlibrary.base.ViewHolder;

import java.util.List;

/**
 * Created by 熊，我 on 2017/8/11.
 */

public class RecommendAdapter extends MultiItemTypeAdapter<RecommendBean.RecommendAppBean> {

    private Context mContext;
    private OnAppDetailItemClickListener mOnAppDetailItemClickListener;

    public RecommendAdapter(Context context, List<RecommendBean.RecommendAppBean> datas) {
        super(context, datas);

        mContext = context;

        addItemViewDelegate(new AppDelegate());
        addItemViewDelegate(new AdDelegate());
    }

    // 水平滑动的App信息
    private class AppDelegate implements ItemViewDelegate<RecommendBean.RecommendAppBean> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_applist_horizontal;
        }

        @Override
        public boolean isForViewType(RecommendBean.RecommendAppBean item, int position) {
            return item.getType() == 0;
        }

        @Override
        public void convert(ViewHolder holder, final RecommendBean.RecommendAppBean recommendAppBean, int position) {
            // 标题
            holder.setText(R.id.tv_item_title, recommendAppBean.getTitle());
            // 列表
            RecyclerView recyclerView = holder.getView(R.id.rv_applist_item);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // 水平滑动
            recyclerView.setLayoutManager(linearLayoutManager);

            AppItemAdapter appItemAdapter = new AppItemAdapter(mContext);
            appItemAdapter.addDataAll(recommendAppBean.getAppList());
            recyclerView.setAdapter(appItemAdapter);

            appItemAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                    if (mOnAppDetailItemClickListener != null) {
                        mOnAppDetailItemClickListener.goAppDetail(recommendAppBean.getAppList().get(position).getPackageName());
                    }
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                    return false;
                }
            });
        }
    }

    // 广告
    private class AdDelegate implements ItemViewDelegate<RecommendBean.RecommendAppBean> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_ad;
        }

        @Override
        public boolean isForViewType(RecommendBean.RecommendAppBean item, int position) {
            return item.getType() == 1;
        }

        @Override
        public void convert(ViewHolder holder, RecommendBean.RecommendAppBean recommendAppBean, int position) {
            // 第一个图片广告
            holder.setImageUrl(R.id.iv_ad1, recommendAppBean.getIconList().get(0));
            // 第二个图片广告
            holder.setImageUrl(R.id.iv_ad2, recommendAppBean.getIconList().get(1));
        }
    }

    // 水平滑动的RecyclerView的Adapter
    public class AppItemAdapter extends CommonAdapter<AppBean> {

        public AppItemAdapter(Context context) {
            super(context, R.layout.item_app);
        }

        @Override
        protected void convert(ViewHolder holder, AppBean appBean, int position) {
            // 应用图标
            holder.setImageUrl(R.id.iv_app_icon, appBean.getIcon());
            // 应用名称
            holder.setText(R.id.tv_app_name, appBean.getName());
            // 应用大小
            holder.setText(R.id.tv_app_size, appBean.getSizeDesc());
        }
    }

    /**
     * Item点击之后的回调
     */
    public interface OnAppDetailItemClickListener {
        void goAppDetail(String packageName);
    }

    public void setOnAppDetailItemClickListener(OnAppDetailItemClickListener listener) {
        mOnAppDetailItemClickListener = listener;
    }
}
