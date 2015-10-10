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
import android.widget.Toast;

import com.lzb.oa.BaseActivity;
import com.lzb.oa.R;

public class SettingAbout extends BaseActivity implements OnClickListener {

    private RelativeLayout layoutSettingVersion;
    private RelativeLayout layoutSettingRecommend;
    private RelativeLayout layoutSettingTest;
    private TextView tvSettingVersion;
    private TextView tvSettingAboutVersion;
    private String version;

    public static void startSettingAbout(Context context) {
        Intent intent = new Intent(context, SettingAbout.class);
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
        layoutSettingVersion = (RelativeLayout) findViewById(R.id.layout_setting_version);
        layoutSettingRecommend = (RelativeLayout) findViewById(R.id.layout_setting_recommend);
        layoutSettingTest = (RelativeLayout) findViewById(R.id.layout_setting_test);
        tvSettingVersion = (TextView) findViewById(R.id.tv_setting_version);
        tvSettingAboutVersion = (TextView) findViewById(R.id.tv_setting_about_version);

        version = "V " + getAppVersionName(this);
        tvSettingVersion.setHint(version);
        tvSettingAboutVersion.setHint(version);

        layoutSettingVersion.setOnClickListener(this);
        layoutSettingRecommend.setOnClickListener(this);
        layoutSettingTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
        case R.id.layout_setting_version:
            Toast.makeText(SettingAbout.this, "当前应用程序版本号为：" + version,
                    Toast.LENGTH_SHORT).show();
            break;

        case R.id.layout_setting_recommend:
            SettingAboutRecommend.startSettingAboutRecommend(SettingAbout.this);
            break;

        case R.id.layout_setting_test:
            String city = "上海";
            String address = "上海市浦东新区浦建路725弄2号";

            break;

        default:
            break;
        }
    }

}
