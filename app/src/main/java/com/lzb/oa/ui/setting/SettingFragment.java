package com.lzb.oa.ui.setting;

import android.app.Fragment;
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
import com.lzb.oa.commons.Constant;
import com.lzb.oa.ui.auth.LoginActivity;

public class SettingFragment extends Fragment implements OnClickListener {

    private final static String NICKNAME_URL = Constant.URL
            + "get_nickname.json";

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
            SettingPerInfo.startSettingPerInfo(getActivity());
            Toast.makeText(getActivity(), "个人信息", Toast.LENGTH_SHORT).show();
            break;

        case R.id.layout_taskinfo:
            SettingTaskInfo.startSettingTaskInfo(getActivity(), empNo);
            Toast.makeText(getActivity(), "已领取任务", Toast.LENGTH_SHORT).show();
            break;

        case R.id.layout_about:
            SettingAbout.startSettingAbout(getActivity());
            Toast.makeText(getActivity(), "关于", Toast.LENGTH_SHORT).show();
            break;

        case R.id.layout_change:
            SettingChangePass.startSettingChangePass(getActivity());
            break;

        case R.id.layout_exit:
            SharedPreferences sp = getActivity().getSharedPreferences(
                    "OAEmpInfo", getActivity().MODE_PRIVATE);
            sp.edit().clear().commit();
            LoginActivity.startLoginActivity(getActivity(), "", "");
            Toast.makeText(getActivity(), "退出", Toast.LENGTH_SHORT).show();
            break;

        default:
            break;
        }
    }

}
