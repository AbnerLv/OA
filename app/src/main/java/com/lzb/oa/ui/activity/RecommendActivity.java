package com.lzb.oa.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.lzb.oa.R;

public class RecommendActivity extends BaseActivity {

    public static void startSettingAboutRecommend(Context context) {
        Intent intent = new Intent(context, RecommendActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_about_recommend);
    }

}
