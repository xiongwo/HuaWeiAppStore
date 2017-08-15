package com.xiongwo.huaweiappstore.mvp.view.fragment;


import android.view.View;
import android.widget.TextView;

import com.xiongwo.huaweiappstore.R;
import com.xiongwo.huaweiappstore.util.UIUtil;
import com.xiongwo.huaweiappstore.view.LoadingPager;
import com.xiongwo.huaweiappstore.view.widget.EnterLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 管理
 * Created by 熊，我 on 2017/8/5.
 */

public class AppManagerFragment extends com.xiongwo.huaweiappstore.base.BaseFragment {

    @BindView(R.id.update_label_textview)
    TextView tvUpdateLabel ;
    @BindView(R.id.update_label_subtitle)
    TextView tvUpdateLabelSubtitle ;
    @BindView(R.id.install_manager_layout)
    EnterLayout installManager ;
    @BindView(R.id.apk_manager_layout)
    EnterLayout apkManager ;
    @BindView(R.id.system_manager_layout)
    EnterLayout systemManager ;
    @BindView(R.id.connect_computer)
    EnterLayout connect ;

    @Override
    protected View createLoadedView() {
        View view = UIUtil.inflate(R.layout.fragment_manager);
        ButterKnife.bind(this, view);

        tvUpdateLabel.setText(UIUtil.getString(R.string.update_manager_title));
        tvUpdateLabelSubtitle.setText(UIUtil.getString(R.string.update_manager_subtitle_version_new));

        installManager.setTitle(UIUtil.getString(R.string.install_manager_title_ex));
        installManager.setMemo(UIUtil.getString(R.string.install_manager_subtitle));

        apkManager.setTitle(UIUtil.getString(R.string.apk_management));
        apkManager.setMemo(UIUtil.getString(R.string.apkmanage_tips_modify));

        systemManager.setTitle(UIUtil.getString(R.string.system_manager_title));
        systemManager.setMemo(UIUtil.getString(R.string.system_manager_memo));

        connect.setTitle(UIUtil.getString(R.string.connect_pc));
        connect.setMemo(UIUtil.getString(R.string.manager_phone_by_pc));

        return view;
    }

    @Override
    protected void load() {
        setState(LoadingPager.LoadResult.success);
    }
}
