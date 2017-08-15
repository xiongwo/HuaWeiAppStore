package com.xiongwo.huaweiappstore.adapter.section;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiongwo.huaweiappstore.R;
import com.xiongwo.huaweiappstore.bean.AppBean;
import com.xiongwo.huaweiappstore.view.widget.DownloadProgressButton;
import com.xiongwo.recyclerviewlibrary.base.ViewHolder;
import com.xiongwo.recyclerviewlibrary.section.StatelessSection;

import java.util.List;

/**
 * Created by 熊，我 on 2017/8/13.
 */

public class TopContactsSection extends StatelessSection {

    private Context mContext;
    private String mTitle;
    private List<AppBean> mAppBeanList;

    public TopContactsSection(Context context, String title, List<AppBean> appBeanList) {
        super(R.layout.applistitem_titlecard, R.layout.applistitem_normal);
        mContext = context;
        mTitle = title;
        mAppBeanList = appBeanList;
    }

    @Override
    public int getContentItemsTotal() {
        return mAppBeanList.size();
    }

    @Override
    public ViewHolder getItemViewHolder(View view, int viewType) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, int position) {
        AppBean appBean = mAppBeanList.get(position);
        holder.setText(R.id.appSerial, appBean.getAliasName());
        holder.setImageUrl(R.id.appicon, appBean.getIcon()) ;
        holder.setText(R.id.ItemTitle, appBean.getName()) ;
        holder.setText(R.id.ItemText_star, appBean.getSizeDesc()) ;
        holder.setText(R.id.memo, appBean.getMemo()) ;
    }

    @Override
    public ViewHolder getHeaderViewHolder(Context context, View view) {
        return new HeaderViewHolder(view) ;
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        super.onBindHeaderViewHolder(holder);
        holder.setText(R.id.ItemTitle, mTitle) ;
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
        }
    }

    class ItemViewHolder extends ViewHolder {

        TextView appSerial ;
        ImageView appicon ;
        DownloadProgressButton downbtn ;
        TextView ItemTitle ;
        TextView ItemText_star ;
        TextView memo ;

        public ItemViewHolder(View view) {
            super(mContext,view);
            appSerial = (TextView) view.findViewById(R.id.appSerial);
            appicon = (ImageView) view.findViewById(R.id.appicon);
            downbtn = (DownloadProgressButton) view.findViewById(R.id.downbtn);
            ItemTitle = (TextView) view.findViewById(R.id.ItemTitle);
            ItemText_star = (TextView) view.findViewById(R.id.ItemText_star);
            memo = (TextView) view.findViewById(R.id.memo);
        }
    }
}
