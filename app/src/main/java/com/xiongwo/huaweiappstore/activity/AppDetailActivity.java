package com.xiongwo.huaweiappstore.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xiongwo.huaweiappstore.R;
import com.xiongwo.huaweiappstore.adapter.AppDetailPagerAdapter;
import com.xiongwo.huaweiappstore.base.BaseMvpActivity;
import com.xiongwo.huaweiappstore.bean.AppDetailBean;
import com.xiongwo.huaweiappstore.mvp.presenter.AppDetailActivityPresenter;
import com.xiongwo.huaweiappstore.mvp.presenter.impl.AppDetailActivityPresenterImpl;
import com.xiongwo.huaweiappstore.mvp.view.fragment.AppCommentFragment;
import com.xiongwo.huaweiappstore.mvp.view.fragment.AppDetailIntroduceFragment;
import com.xiongwo.huaweiappstore.mvp.view.fragment.AppDetailRecommendFragment;
import com.xiongwo.huaweiappstore.mvp.view.fragment.AppRecommendFragment;
import com.xiongwo.huaweiappstore.mvp.view.view.AppDetailActivityView;
import com.xiongwo.huaweiappstore.util.LogUtil;
import com.xiongwo.huaweiappstore.util.UIUtil;
import com.xiongwo.huaweiappstore.view.widget.SubTabNavigator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by 熊，我 on 2017/8/14.
 */

public class AppDetailActivity extends BaseMvpActivity<AppDetailActivityPresenter> implements AppDetailActivityView {

    private String mPackageName;
    private AppDetailBean mAppDetailBean;
    private boolean mIsExpand = false;
    private List<Fragment> mFragmentList;

    // 导航栏
    @BindView(R.id.title_text)
    TextView title_text ;
    @BindView(R.id.iv_search)
    ImageView iv_search ;

    // 头部基本信息
    @BindView(R.id.detail_head_app_icon_imageview)
    ImageView detail_app_icon ;
    @BindView(R.id.detail_head_app_name_textview)
    TextView detail_app_name ;
    @BindView(R.id.detail_head_download_count_textview)
    TextView detail_app_download_count ;
    @BindView(R.id.detail_head_app_stars_ratingbar)
    RatingBar detail_app_stars ;

    // 头部动态加载的信息，标签
    @BindView(R.id.detail_head_label_layout_linearlayout)
    RelativeLayout detail_head_label_layout ;
    @BindView(R.id.detail_head_label_icon_layout_linearlayout)
    LinearLayout detail_head_label_icon_layout ;
    @BindView(R.id.detail_head_lable_folding_textview)
    TextView detail_head_lable_folding ;
    // 头部动态加载的信息，检测
    @BindView(R.id.detail_head_safe_icon_layout_linearlayout)
    LinearLayout detail_head_safe_icon_layout ;
    @BindView(R.id.detail_head_safe_icon_container_linearlayout)
    LinearLayout detail_head_safe_icon_container ;

    // 滑动页，类似TabLayout加ViewPager的效果
    @BindView(R.id.subTab)
    SubTabNavigator subTabNavigator ;
    @BindView(R.id.vp)
    ViewPager mViewPager ;

    @Inject
    public AppDetailActivityPresenterImpl mAppDetailActivityPresenterImpl;

    @Override
    public void onAppDetailDataSuccess(AppDetailBean appDetailBean) {
        LogUtil.information("AppDetail --> " + appDetailBean.getLabelNameList().size());
        mAppDetailBean = appDetailBean;
        setDetailHead();
    }

    @Override
    public void onAppDetailDataError(String errorMsg) {
        LogUtil.information("AppDetail 加载错误");
        setContentView(R.layout.loading_page_empty);
    }

    @Override
    protected AppDetailActivityPresenter initInject() {
        mActivityComponent.inject(AppDetailActivity.this);
        LogUtil.information("Impl --> ");
        return mAppDetailActivityPresenterImpl;
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_app_detail2);
    }

    @Override
    protected void initView() {
        // 设置导航栏
        setStatusBar();
        iv_search.setVisibility(View.VISIBLE);
        if (title_bar != null) {
            title_bar.setBackgroundResource(R.color.black_alpha_5);
        }
        title_text.setText(getResources().getString(R.string.title_activity_app_detail));
    }

    @Override
    protected void initData() {
        mPackageName = getIntent().getStringExtra("packageName");
        mAppDetailActivityPresenterImpl.getAppDetailData(this, mPackageName);
    }

    private void setDetailHead() {
        Glide.with(UIUtil.getContext()).load(mAppDetailBean.getIcoUrl()).into(detail_app_icon) ;
        detail_app_name.setText(mAppDetailBean.getName());
        detail_app_download_count.setText(mAppDetailBean.getIntro());
        detail_app_stars.setRating(Float.parseFloat(mAppDetailBean.getStars()));

        setLabel();
        setSafeLabel();
        setSubTab();

        // 点击，展开或关闭检测应用的描述
        detail_head_label_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsExpand) {
                    mIsExpand = false;
                    detail_head_safe_icon_layout.setVisibility(View.GONE);
                    detail_head_lable_folding.setBackgroundResource(R.drawable.ic_public_arrow_down);
                } else {
                    mIsExpand = true;
                    detail_head_safe_icon_layout.setVisibility(View.VISIBLE);
                    detail_head_lable_folding.setBackgroundResource(R.drawable.ic_public_arrow_up);
                }
            }
        });
    }

    /**
     * 动态地添加应用标签
     */
    private void setLabel(){
        for(AppDetailBean.LabelName labelNamesBean : mAppDetailBean.getLabelNameList()){
            View labelView = UIUtil.inflate(R.layout.appdetail_item_head_label_item);
            TextView label = (TextView) labelView.findViewById(R.id.appdetail_head_label_textview);
            label.setText(labelNamesBean.getName());
            if(labelNamesBean.getType() == 1){
                label.setTextColor(getResources().getColor(R.color.app_not_safe_textcolor));
            }
            // 动态地添加应用标签
            detail_head_label_icon_layout.addView(labelView);
        }
    }

    /**
     * 动态地添加应用检测描述
     */
    private void setSafeLabel(){
        for(AppDetailBean.SafeLabel safeLabelsBean : mAppDetailBean.getSafeLabelList()){
            View safeLabelView = UIUtil.inflate(R.layout.appdetail_item_head_safe_item);
            TextView safe_checker = (TextView) safeLabelView.findViewById(R.id.safe_checker_textview);
            TextView safe_checker_label = (TextView) safeLabelView.findViewById(R.id.safe_checker_label);
            ImageView detail_head_app_icon = (ImageView) safeLabelView.findViewById(R.id.detail_head_app_icon_imageview);
            TextView detail_safe_desc_textview = (TextView) safeLabelView.findViewById(R.id.detail_safe_desc_textview);

            safe_checker.setText(safeLabelsBean.getDetectorName());
            safe_checker_label.setText(safeLabelsBean.getDetectorDesc());
            Glide.with(UIUtil.getContext()).load(safeLabelsBean.getUrl()).into(detail_head_app_icon);
            detail_safe_desc_textview.setText(safeLabelsBean.getName());
            // 动态地添加应用检测描述
            detail_head_safe_icon_container.addView(safeLabelView);
        }
    }

    /**
     * 设置类似类似TabLayout加ViewPager的效果
     */
    private void setSubTab() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new AppDetailIntroduceFragment());
        mFragmentList.add(new AppCommentFragment());
        mFragmentList.add(new AppRecommendFragment());

        AppDetailPagerAdapter appDetailPagerAdapter = new AppDetailPagerAdapter(getSupportFragmentManager());
        appDetailPagerAdapter.setFragmentList(mFragmentList);

        mViewPager.setAdapter(appDetailPagerAdapter);
        // 这里解决了滑动ViewPager时，也改变文本指示，也就是文本高亮
        mViewPager.setOnPageChangeListener(subTabNavigator);

        // 将ViewPager传进去，进而实现，点击文本时，改变ViewPager当前的条目
        subTabNavigator.setViewPager(mViewPager);
        subTabNavigator.setLeftText(mAppDetailBean.getTabInfoList().get(0));
        subTabNavigator.setNoneText(mAppDetailBean.getTabInfoList().get(1));
        subTabNavigator.setRightText(mAppDetailBean.getTabInfoList().get(2));
    }

    public String getAppPackageName(){
        return mPackageName ;
    }
}
