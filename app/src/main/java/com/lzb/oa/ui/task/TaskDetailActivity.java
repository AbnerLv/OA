package com.lzb.oa.ui.task;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.lzb.oa.BaseActivity;
import com.lzb.oa.R;
import com.lzb.oa.commons.Constant;
import com.lzb.oa.commons.Period;
import com.lzb.oa.entity.TaskInfo;
import com.lzb.oa.service.TaskManaService;
import com.lzb.oa.service.handler.DealTaskHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class TaskDetailActivity extends BaseActivity implements OnClickListener {

    private static final String TAG = "TaskDetailActivity";

    private TextView tvTaskDetailNo;
    private TextView tvTaskDetailName;
    private TextView tvTaskDetailSex;
    private TextView tvTaskDetailPhoneNo;
    private TextView tvTaskDetailDate; // 时间
    private TextView tvTaskDetailPeriod;
    private TextView tvTaskDetailAddress;
    private TextView tvTaskDetailComplete;
    private Button btnTaskDetailCancel;
    private Button btnTaskDetailOk;
    private JSONObject json = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.task_detail);
        init();

    }

    private void init() {
        tvTaskDetailNo = (TextView) findViewById(R.id.tv_task_detail_no);
        tvTaskDetailName = (TextView) findViewById(R.id.tv_task_detail_name);
        tvTaskDetailSex = (TextView) findViewById(R.id.tv_task_detail_sex);
        tvTaskDetailPhoneNo = (TextView) findViewById(R.id.tv_task_detail_phone_no);
        tvTaskDetailDate = (TextView) findViewById(R.id.tv_task_detail_date);
        tvTaskDetailPeriod = (TextView) findViewById(R.id.tv_task_detail_period);
        tvTaskDetailAddress = (TextView) findViewById(R.id.tv_task_detail_address);
        tvTaskDetailComplete = (TextView) findViewById(R.id.tv_task_detail_complete);
        btnTaskDetailCancel = (Button) findViewById(R.id.btn_task_detail_cancel);
        btnTaskDetailOk = (Button) findViewById(R.id.btn_task_detail_ok);

        Intent intent = getIntent();
        TaskInfo taskInfo = (TaskInfo) getIntent().getSerializableExtra("task_data");
        Log.e(TAG, "intent before" + taskInfo.getRoomer_no());
        Log.e("intent", intent.toString());
        tvTaskDetailNo.setText("客户编号：" + taskInfo.getRoomer_no());
        tvTaskDetailName.setText("客户姓名：" + taskInfo.getRoomer_name());
        tvTaskDetailSex.setText("客户性别：" + taskInfo.getRoomer_sex());
        tvTaskDetailPhoneNo.setText("联系方式：" + taskInfo.getRoomer_phone_no());
        tvTaskDetailDate.setText("看房日期：" + taskInfo.getRoomer_date());
        tvTaskDetailPeriod.setText("看房时间："
                + Period.getString(Integer.parseInt(taskInfo.getRoomer_period())));
        tvTaskDetailAddress.setText("详细地址：" + taskInfo.getHouse_address());

        tvTaskDetailAddress.setOnClickListener(this);
        btnTaskDetailCancel.setOnClickListener(this);
        btnTaskDetailOk.setOnClickListener(this);
        if ("".equals(taskInfo.getRoomer_emp_no())) {
            btnTaskDetailCancel.setVisibility(View.GONE);
        } else {
            tvTaskDetailComplete.setVisibility(View.VISIBLE);
            if (Integer.parseInt(taskInfo.getRoomer_complete()) == 0) {
                tvTaskDetailComplete.setText("正在跟进中…");
            } else {
                tvTaskDetailComplete.setText("交易已完成！");
                btnTaskDetailCancel.setVisibility(View.GONE);
                btnTaskDetailOk.setVisibility(View.GONE);
            }
            btnTaskDetailOk.setVisibility(View.GONE);
        }
        try {
            json = new JSONObject();
            SharedPreferences sp = getSharedPreferences(Constant.EMPINFO,
                    MODE_PRIVATE);
            json.put("emp_no", sp.getString("emp_no", ""));
            json.put("roomer_no", taskInfo.getRoomer_no());
            json.put("roomer_house_no", taskInfo.getRoomer_house_no());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_task_detail_cancel:
                TaskManaService.getInstance().cancelTask(getApplicationContext(), json,
                        new DealTaskHandler() {
                            @Override
                            public void onSuccess(int success) {
                                    if (success == 1) {
                                        tvTaskDetailComplete
                                                .setVisibility(View.GONE);
                                        btnTaskDetailOk.setVisibility(View.VISIBLE);
                                        btnTaskDetailCancel
                                                .setVisibility(View.GONE);
                                        Toast.makeText(TaskDetailActivity.this, "任务已取消",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                        });
                break;

            case R.id.btn_task_detail_ok:
                TaskManaService.getInstance().selectTask(getApplicationContext(), json,
                        new DealTaskHandler() {
                            @Override
                            public void onSuccess(int success) {
                                if (success == 1) {
                                    tvTaskDetailComplete
                                            .setVisibility(View.VISIBLE);
                                    tvTaskDetailComplete.setText("正在跟进中…");
                                    btnTaskDetailOk.setVisibility(View.GONE);
                                    btnTaskDetailCancel
                                            .setVisibility(View.VISIBLE);
                                    Toast.makeText(TaskDetailActivity.this, "你已成功领取任务",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        TaskManaService.getInstance().cancelPendingRequests();
    }
}
