package com.lzb.oa.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzb.oa.BaseActivity;
import com.lzb.oa.R;

public class SettingAboutActivity extends BaseActivity implements OnClickListener {

    private RelativeLayout layoutSettingRecommend;
    private TextView tvSettingAboutVersion;
    private String version;

    public static void startSettingAbout(Context context) {
        Intent intent = new Intent(context, SettingAboutActivity.class);
        context.startActivity(intent);
    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_about);

        init();
    }

    private void init() {
        layoutSettingRecommend = (RelativeLayout) findViewById(R.id.layout_setting_recommend);
        tvSettingAboutVersion = (TextView) findViewById(R.id.tv_setting_about_version);

        version = " " + getAppVersionName(this);
        tvSettingAboutVersion.setHint("移动办公 v" + version);

        layoutSettingRecommend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

        case R.id.layout_setting_recommend:
            RecommendActivity.startSettingAboutRecommend(SettingAboutActivity.this);
            break;

        default:
            break;
        }
    }

}
