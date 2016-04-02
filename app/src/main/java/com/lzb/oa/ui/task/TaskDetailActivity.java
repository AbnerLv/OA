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

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lzb.oa.BaseActivity;
import com.lzb.oa.R;
import com.lzb.oa.commons.Constant;
import com.lzb.oa.commons.Period;
import com.lzb.oa.entity.TaskInfo;

import org.json.JSONException;
import org.json.JSONObject;

public class TaskDetailActivity extends BaseActivity implements OnClickListener {

    private static final String TAG = "TaskDetailActivity";
    private static final String TASK_DETAIL_URL = Constant.URL
            + "task/get_task.json";
    private static final String CANCEL_TASK_URL = Constant.URL
            + "task/cancel_task.json";

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
    private RequestQueue mQueue = null;
    private JSONObject json = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.task_detail);
        mQueue = Volley.newRequestQueue(getApplicationContext());
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
        Log.e(TAG,"intent before");
        TaskInfo taskInfo = (TaskInfo)getIntent().getSerializableExtra("task_data");
        Log.e(TAG,"intent before"+taskInfo.getRoomer_no());
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
            json.put("roomer_no", intent.getStringExtra("roomerNo"));
            json.put("roomer_house_no", intent.getStringExtra("roomerHouseNo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        case R.id.btn_task_detail_cancel:
            JsonObjectRequest jsonObjectRequestCancel = new JsonObjectRequest(
                    CANCEL_TASK_URL, json, new Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (Integer.parseInt(response
                                        .getString("success")) == 1) {
                                    Toast.makeText(TaskDetailActivity.this, "任务已取消",
                                            Toast.LENGTH_SHORT).show();
                                    tvTaskDetailComplete
                                            .setVisibility(View.GONE);
                                    btnTaskDetailOk.setVisibility(View.VISIBLE);
                                    btnTaskDetailCancel
                                            .setVisibility(View.GONE);
                                } else {
                                    Toast.makeText(TaskDetailActivity.this,
                                            "取消任务失败，请稍后再试…", Toast.LENGTH_SHORT)
                                            .show();
                                }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(TaskDetailActivity.this,
                                    "任务取消失败，请耐心等待5秒后再试！", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });
            mQueue.add(jsonObjectRequestCancel);
            break;

        case R.id.btn_task_detail_ok:
            JsonObjectRequest jsonObjectRequestOk = new JsonObjectRequest(
                    TASK_DETAIL_URL, json, new Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (Integer.parseInt(response
                                        .getString("success")) == 1) {
                                    Toast.makeText(TaskDetailActivity.this, "你已成功领取任务",
                                            Toast.LENGTH_SHORT).show();
                                    tvTaskDetailComplete
                                            .setVisibility(View.VISIBLE);
                                    tvTaskDetailComplete.setText("正在跟进中…");
                                    btnTaskDetailOk.setVisibility(View.GONE);
                                    btnTaskDetailCancel
                                            .setVisibility(View.VISIBLE);
                                } else {
                                    Toast.makeText(TaskDetailActivity.this,
                                            "领取任务失败，请耐心等待5秒后再尝试！",
                                            Toast.LENGTH_SHORT).show();
                                }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(TaskDetailActivity.this, "网络连接失败",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
            mQueue.add(jsonObjectRequestOk);
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

}
