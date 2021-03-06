package com.lzb.oa.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzb.oa.R;
import com.lzb.oa.ui.activity.HaveTaskActivity;
import com.lzb.oa.ui.activity.ModifyPasswordActivity;
import com.lzb.oa.ui.activity.SettingAboutActivity;
import com.lzb.oa.ui.activity.ShowPerInfoActivity;
import com.lzb.oa.ui.activity.UserLoginActivity;

public class SettingFragment extends Fragment implements OnClickListener {


    private LinearLayout settingPerinfo;
    private LinearLayout settingTaskinfo;
    private LinearLayout settingAbout;
    private LinearLayout settingExit;
    private LinearLayout settingChange;
    private TextView tvPerdetail;
    private String empNo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View settingLayout = inflater.inflate(R.layout.setting_layout,
                container, false);
        return settingLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init() {
        settingPerinfo = (LinearLayout) getActivity().findViewById(
                R.id.layout_perinfo);
        settingTaskinfo = (LinearLayout) getActivity().findViewById(
                R.id.layout_taskinfo);
        settingAbout = (LinearLayout) getActivity().findViewById(
                R.id.layout_about);
        settingChange = (LinearLayout) getActivity().findViewById(
                R.id.layout_change);
        settingExit = (LinearLayout) getActivity().findViewById(
                R.id.layout_exit);
        tvPerdetail = (TextView) getActivity().findViewById(R.id.tv_perdetail);
        SharedPreferences sp = getActivity().getSharedPreferences("OAEmpInfo",
                0);
        String name = sp.getString("emp_nickname", null);
        empNo = sp.getString("emp_no", null);
        tvPerdetail.setHint(name);

        settingPerinfo.setOnClickListener(this);
        settingTaskinfo.setOnClickListener(this);
        settingAbout.setOnClickListener(this);
        settingChange.setOnClickListener(this);
        settingExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.layout_perinfo:
            ShowPerInfoActivity.startSettingPerInfo(getActivity());
            Toast.makeText(getActivity(), "个人信息", Toast.LENGTH_SHORT).show();
            break;

        case R.id.layout_taskinfo:
            HaveTaskActivity.startSettingTaskInfo(getActivity(), empNo);
            Toast.makeText(getActivity(), "已领取任务", Toast.LENGTH_SHORT).show();
            break;

        case R.id.layout_about:
            SettingAboutActivity.startSettingAbout(getActivity());
            Toast.makeText(getActivity(), "关于", Toast.LENGTH_SHORT).show();
            break;

        case R.id.layout_change:
            Intent intent = new Intent(getActivity(), ModifyPasswordActivity.class);
            startActivity(intent);
            break;

        case R.id.layout_exit:
            SharedPreferences sp = getActivity().getSharedPreferences(
                    "OAEmpInfo", getActivity().MODE_PRIVATE);
            sp.edit().clear().commit();
            Toast.makeText(getActivity(), "退出", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(getActivity(), UserLoginActivity.class);
            startActivity(intent1);
            break;

        default:
            break;
        }
    }

}
