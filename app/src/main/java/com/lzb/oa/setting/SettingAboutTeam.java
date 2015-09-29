package com.lzb.oa.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.lzb.oa.BaseActivity;
import com.lzb.oa.R;

public class SettingAboutTeam extends BaseActivity {

    public static void startSettingAboutTeam(Context context) {
        Intent intent = new Intent(context, SettingAboutTeam.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_about_team);
    }

}
