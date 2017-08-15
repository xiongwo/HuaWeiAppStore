package com.xiongwo.huaweiappstore.base;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.xiongwo.huaweiappstore.R;
import com.xiongwo.huaweiappstore.util.AppActivityManager;
import com.xiongwo.huaweiappstore.util.LogUtil;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activity基类
 * 配置应用属性，提供设置沉浸式状态栏的方法，抽象初始化布局、控件、数据的方法，绑定与解绑ButterKnife
 * Created by 熊，我 on 2017/8/4.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    private Unbinder mUnbinder;
    protected ViewGroup title_bar = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //强制竖屏
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // 去掉ActionBar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        AppActivityManager.getInstance().addActivityToStack(this);

        initLayout();
        mUnbinder = ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    /**
     * 初始化布局
     */
    protected abstract void initLayout();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 设置沉浸式状态栏
     */
    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            title_bar = (ViewGroup) findViewById(R.id.bar_layout);
            final int statusHeight = getStatusBarHeight();
            title_bar.post(new Runnable() {
                @Override
                public void run() {
                    int titleHeight = title_bar.getHeight();
                    android.widget.RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) title_bar.getLayoutParams();
                    params.height = statusHeight + titleHeight;
                    title_bar.setLayoutParams(params);
                }
            });
        }
    }

    /**
     * 获取状态栏的高度
     * @return
     */
    protected int getStatusBarHeight(){
        try
        {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return  getResources().getDimensionPixelSize(x);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
