package com.xiongwo.huaweiappstore.mvp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xiongwo.huaweiappstore.R;
import com.xiongwo.huaweiappstore.activity.AppDetailActivity;
import com.xiongwo.huaweiappstore.activity.GalleryActivity;
import com.xiongwo.huaweiappstore.base.BaseMvpFragment;
import com.xiongwo.huaweiappstore.bean.AppIntroductionBean;
import com.xiongwo.huaweiappstore.mvp.presenter.AppIntroductionFragmentPresenter;
import com.xiongwo.huaweiappstore.mvp.presenter.impl.AppIntroductionFragmentPresenterImpl;
import com.xiongwo.huaweiappstore.mvp.view.view.AppIntroductionFragmentView;
import com.xiongwo.huaweiappstore.util.UIUtil;
import com.xiongwo.huaweiappstore.view.LoadingPager;
import com.xiongwo.huaweiappstore.view.widget.FlowLayout;
import com.xiongwo.huaweiappstore.view.widget.FoldingTextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊，我 on 2017/8/14.
 */

public class AppDetailIntroduceFragment extends BaseMvpFragment<AppIntroductionFragmentPresenter> implements AppIntroductionFragmentView, View.OnClickListener {

    private AppIntroductionBean mAppIntroductionBean ;

    @BindView(R.id.app_detail_gallery_container_linearlayout)
    LinearLayout app_detail_gallery_container ;
    @BindView(R.id.detail_appinfo_tariff_textview)
    TextView appInfoTariff ;
    @BindView(R.id.detail_appinfo_size_textview)
    TextView appInfoSize ;
    @BindView(R.id.detail_appinfo_version_textview)
    TextView appInfoVersion ;
    @BindView(R.id.detail_appinfo_release_date_textview)
    TextView appInfoDate ;
    @BindView(R.id.appdetail_appinfo_developer_textview)
    TextView appInfoDeveloper ;
    @BindView(R.id.ll)
    LinearLayout appInfoDes ;
    @BindView(R.id.flowLayout)
    FlowLayout flowLayout ;

    @Inject
    public AppIntroductionFragmentPresenterImpl appIntroductionFragmentPresenter ;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    protected View createLoadedView() {
        View view = UIUtil.inflate(R.layout.fragment_app_introduction) ;
        ButterKnife.bind(this,view) ;

        // 图片集
        for(int i = 0 ;i < mAppIntroductionBean.getImageCompressList().size() ; i ++){
            String url = mAppIntroductionBean.getImageCompressList().get(i);
            View screenView = View.inflate(getContext(),R.layout.appdetail_item_screen_image,null) ;
            ImageView screenImageView = (ImageView) screenView.findViewById(R.id.appdetail_screen_img_imageview);
            screenImageView.setContentDescription(screenImageView.getResources().getString(R.string.appdetail_screenshot));
            screenImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            screenView.setOnClickListener(this);
            screenView.setTag(i);
            Glide.with(UIUtil.getContext()).load(url).into(screenImageView);
            app_detail_gallery_container.addView(screenView);
        }

        appInfoTariff.setText(mAppIntroductionBean.getAppInfoBean().getTariffDesc());
        appInfoSize.setText(Formatter.formatFileSize(getContext(),Long.parseLong(mAppIntroductionBean.getAppInfoBean().getSize())));
        appInfoDate.setText(mAppIntroductionBean.getAppInfoBean().getReleaseDate());
        appInfoVersion.setText(mAppIntroductionBean.getAppInfoBean().getVersion());
        appInfoDeveloper.setText(mAppIntroductionBean.getAppInfoBean().getDeveloper());

        // 可伸缩的TextView
        for(int i = 0 ; i < mAppIntroductionBean.getAppDetailInfoBeanList().size() ; i ++){
            FoldingTextView foldingTextView = new FoldingTextView(getContext()) ;
            foldingTextView.setTitle(mAppIntroductionBean.getAppDetailInfoBeanList().get(i).getTitle());
            foldingTextView.setContent(mAppIntroductionBean.getAppDetailInfoBeanList().get(i).getBody());
            appInfoDes.addView(foldingTextView);
        }

        // 应用标签
        List<String> tagList = mAppIntroductionBean.getTagList();
        for(int i = 0 ; i < tagList.size() ; i ++){
            View labView = UIUtil.inflate(R.layout.appdetail_item_label_item) ;
            TextView tv = (TextView) labView.findViewById(R.id.appdetail_label_content_textview);
            tv.setText(tagList.get(i));
            flowLayout.addView(labView);
        }

        return view;
    }

    @Override
    protected void load() {
        appIntroductionFragmentPresenter.getAppIntroductionData(mBaseActivity,((AppDetailActivity)getActivity()).getAppPackageName());
    }

    @Override
    public void onAppIntroductionDataSuccess(AppIntroductionBean appIntroductionBean) {
        mAppIntroductionBean = appIntroductionBean ;
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public void onAppIntroductionDataError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected AppIntroductionFragmentPresenter initInject() {
        mFragmentComponent.inject(this);
        return appIntroductionFragmentPresenter;
    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        List<String> images = mAppIntroductionBean.getImagesList();
        Intent intent = new Intent(getContext(), GalleryActivity.class);
        intent.putExtra("tag",tag) ;
        intent.putStringArrayListExtra("urlList", (ArrayList<String>) images);
        getActivity().startActivity(intent) ;
    }
}
