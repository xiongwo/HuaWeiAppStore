package com.xiongwo.huaweiappstore.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.widget.Button;

import com.xiongwo.huaweiappstore.R;
import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.util.AppActivityManager;
import com.xiongwo.huaweiappstore.util.ConstantUtil;
import com.xiongwo.huaweiappstore.util.LogUtil;
import com.xiongwo.huaweiappstore.util.SharedPreferencedUtil;
import com.xiongwo.huaweiappstore.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 欢迎页
 * Created by 熊，我 on 2017/8/5.
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.enter_button)
    Button enterBtn;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initView() {
        initData();
    }

    private void initData() {
        // 返回false，说明不是首次打开应用，就直接打开主页
        if (!SharedPreferencedUtil.getBoolean(this, ConstantUtil.SP_IS_FIRST, true)) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
            AppActivityManager.getInstance().removeActivityFromStack(this);
        }
    }

    @OnClick(R.id.enter_button)
    public void enterHome() {
        initPermission();

        // 保存标志，下次打开时，不会再显示欢迎页
        SharedPreferencedUtil.putBoolean(SplashActivity.this, ConstantUtil.SP_IS_FIRST, false);
    }

    /**
     * 检测SD卡是否已经授权
     */
    private void initPermission() {
        // 6.0之后，对应用数据的管理变得严格，仅在Manifest中声明权限是不够的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PermissionChecker.PERMISSION_GRANTED) {
                // 已经授权SD卡，打开主页
                startActivity(new Intent(this, HomeActivity.class));
                finish();
                AppActivityManager.getInstance().removeActivityFromStack(this);
            } else {
                // 申请SD卡的授权
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LogUtil.information("grantResults size --> " + grantResults.length);
        // 申请成功与否，都会打开主页
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            ToastUtil.showToast(this, "SD卡授权成功！");
        } else {
            ToastUtil.showToast(this, "SD卡没有授权，可能会影响应用的使用");
        }
        startActivity(new Intent(this, HomeActivity.class));
        finish();
        AppActivityManager.getInstance().removeActivityFromStack(this);
    }
}
