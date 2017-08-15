package com.xiongwo.huaweiappstore.adapter.section;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiongwo.huaweiappstore.R;
import com.xiongwo.huaweiappstore.bean.CategoryBean;
import com.xiongwo.recyclerviewlibrary.base.ViewHolder;
import com.xiongwo.recyclerviewlibrary.section.StatelessSection;

import java.util.List;

/**
 * Created by 熊，我 on 2017/8/12.
 */

public class CategoryContactsSection extends StatelessSection {

    private Context mContext;
    private String mTitle;
    private List<CategoryBean.CategoryDataBean> mDataBeen;

    public CategoryContactsSection(Context context, String title, List<CategoryBean.CategoryDataBean> dataBeen) {
        super(R.layout.applistitem_titlecard, R.layout.applistitem_subcat);
        mContext = context;
        mTitle = title;
        mDataBeen = dataBeen;
    }

    @Override
    public int getContentItemsTotal() {
        return mDataBeen.size();
    }

    @Override
    public ViewHolder getItemViewHolder(View view, int viewType) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, int position) {
        CategoryBean.CategoryDataBean categoryDataBean = mDataBeen.get(position);
        // 设置图标
        holder.setImageUrl(R.id.appicon,categoryDataBean.getIconUrl());
        // 设置应用名
        holder.setText(R.id.ItemTitle,categoryDataBean.getName());
    }

    @Override
    public ViewHolder getHeaderViewHolder(Context context, View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        holder.setText(R.id.ItemTitle, mTitle);
    }

    class HeaderViewHolder extends ViewHolder {

        TextView tvTitle;
        TextView tvMore ;
        ImageView ivMore ;

        public HeaderViewHolder(View view) {
            super(mContext,view);
            tvTitle = (TextView) view.findViewById(R.id.ItemTitle);
            tvMore = (TextView) view.findViewById(R.id.downbtn);
            ivMore = (ImageView) view.findViewById(R.id.arrow_right);

            tvMore.setVisibility(View.GONE);
            ivMore.setVisibility(View.GONE);

        }
    }

    class ItemViewHolder extends ViewHolder {

        ImageView appicon ;
        TextView ItemTitle ;

        public ItemViewHolder(View view) {
            super(mContext,view);
            appicon = (ImageView) view.findViewById(R.id.appicon);
            ItemTitle = (TextView) view.findViewById(R.id.ItemTitle);
        }
    }
}
