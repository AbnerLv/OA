package com.lzb.oa.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.lzb.oa.BaseActivity;
import com.lzb.oa.R;
import com.lzb.oa.commons.Constant;
import com.lzb.oa.service.request.JSONArrayRequest;
import com.lzb.oa.ui.adapter.TaskManaAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class SettingTaskInfo extends BaseActivity {

    private final static String PER_TASK_INFO_URL = Constant.URL
            + "my_task.json";

    private ListView lvSettingTaskInfo;
   // private TaskManaAdapter taskManaAdapter = null;
    private RequestQueue mQueue = null;

    public static void startSettingTaskInfo(Context context, String emp_no) {
        Intent intent = new Intent(context, SettingTaskInfo.class);
        intent.putExtra("emp_no", emp_no);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_task_info);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sendToServer(this.getApplicationContext());
    }

    private void init() {
        lvSettingTaskInfo = (ListView) findViewById(R.id.lv_setting_task_info);
        //taskManaAdapter = new TaskManaAdapter(this);
    }

    private void sendToServer(Context context) {
        final ArrayList<HashMap<String, Object>> listTask = new ArrayList<>();
        mQueue = Volley.newRequestQueue(context);
        /*
        try {
            Intent intent = getIntent();
            JSONObject json = new JSONObject();
            json.put("emp_no", intent.getStringExtra("emp_no"));

            JSONArrayRequest jsonArrayRequest = new JSONArrayRequest(
                    Request.Method.POST, PER_TASK_INFO_URL, json,
                    new Listener<JSONArray>() {

                        @Override
                        public void onResponse(JSONArray response) {

                            Log.e("TAG", response.toString());
                            for (int i = 0; i < response.length(); i++) {
                                HashMap<String, Object> map = new HashMap<String, Object>();
                                try {
                                    JSONObject jObj = new JSONObject(response
                                            .get(i).toString());
                                    map.put("roomer_no", jObj.get("roomer_no"));
                                    map.put("roomer_name",
                                            jObj.get("roomer_name"));
                                    map.put("roomer_sex",
                                            jObj.get("roomer_sex"));
                                    map.put("roomer_phone_no",
                                            jObj.get("roomer_phone_no"));
                                    map.put("roomer_house_no",
                                            jObj.get("roomer_house_no"));
                                    map.put("roomer_date",
                                            jObj.get("roomer_date"));
                                    map.put("roomer_period",
                                            jObj.get("roomer_period"));
                                    map.put("roomer_rent",
                                            jObj.get("roomer_rent"));
                                    map.put("roomer_complete",
                                            jObj.get("roomer_complete"));
                                    map.put("roomer_emp_no",
                                            jObj.get("roomer_emp_no"));
                                    map.put("house_city",
                                            jObj.get("house_city"));
                                    map.put("house_address",
                                            jObj.get("house_address"));
                                    listTask.add(map);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            taskManaAdapter.setItemList(listTask);
                            taskManaAdapter.notifyDataSetChanged();
                            lvSettingTaskInfo.setAdapter(taskManaAdapter);
                        }
                    }, new ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(SettingTaskInfo.this, "获取数据失败",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
            mQueue.add(jsonArrayRequest);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        */

    }



}
