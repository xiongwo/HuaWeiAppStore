package com.xiongwo.huaweiappstore.mvp.view.fragment;

import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.xiongwo.huaweiappstore.R;
import com.xiongwo.huaweiappstore.adapter.MySubAdapter;
import com.xiongwo.huaweiappstore.bean.MyGvBean;
import com.xiongwo.huaweiappstore.util.UIUtil;
import com.xiongwo.huaweiappstore.view.LoadingPager;
import com.xiongwo.huaweiappstore.view.widget.MyEnterLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的
 * Created by 熊，我 on 2017/8/5.
 */

public class MyFragment extends com.xiongwo.huaweiappstore.base.BaseFragment {

    @BindView(R.id.gv_title_grid)
    GridView gv_title_grid ;
    @BindView(R.id.book_game_layout)
    MyEnterLayout book_game_layout ;
    @BindView(R.id.buy_layout)
    MyEnterLayout buy_layout ;
    @BindView(R.id.huaban_layout)
    MyEnterLayout huaban_layout ;
    @BindView(R.id.setting_computer)
    MyEnterLayout setting_computer ;
    @BindView(R.id.faq_layout)
    MyEnterLayout faq_layout ;
    @BindView(R.id.check_update_layout)
    MyEnterLayout check_update_layout ;
    @BindView(R.id.about_layout)
    MyEnterLayout about_layout ;

    private List<MyGvBean> gvBeanList = new ArrayList<>() ;

    @Override
    protected View createLoadedView() {
        View view = UIUtil.inflate(R.layout.fragment_my);
        ButterKnife.bind(this, view);

        gvBeanList.add(new MyGvBean(UIUtil.getString(R.string.market_prize),R.drawable.icon_market_lucky_draw));
        gvBeanList.add(new MyGvBean(UIUtil.getString(R.string.market_gift),R.drawable.ic_mine_package_normal));
        gvBeanList.add(new MyGvBean(UIUtil.getString(R.string.appzone_comments),R.drawable.icon_market_comment));
        gvBeanList.add(new MyGvBean(UIUtil.getString(R.string.market_mine_message),R.drawable.icon_market_message));

        MySubAdapter adapter = new MySubAdapter(getContext(),gvBeanList) ;
        gv_title_grid.setNumColumns(gvBeanList.size());
        gv_title_grid.setAdapter(adapter);

        book_game_layout.setTitle(UIUtil.getString(R.string.reserve_warpup_game_str));
        buy_layout.setTitle(UIUtil.getString(R.string.purchase_title));
        huaban_layout.setTitle(UIUtil.getString(R.string.mine_point_area));
        setting_computer.setTitle(UIUtil.getString(R.string.action_settings));
        faq_layout.setTitle(UIUtil.getString(R.string.menu_feedback));
        check_update_layout.setTitle(UIUtil.getString(R.string.settings_check_version_update));
        about_layout.setTitle(UIUtil.getString(R.string.about));

        return view;
    }

    @Override
    protected void load() {
        setState(LoadingPager.LoadResult.success);
    }
}
