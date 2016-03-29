package com.lzb.oa.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lzb.oa.commons.Constant;
import com.lzb.oa.service.response.ErrorResponse;
import com.lzb.oa.ui.adapter.TaskManaAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lvzhenbin on 2015/10/9.
 */
public class TaskManaService {

    public static TaskManaService instance;
    private RequestQueue mQueue;
    private ArrayList<HashMap<String, Object>> totalData = new ArrayList<>();
    private int pageNo = 1;//设置pageNo的初始化值为1，即默认获取的是第一页的数据。

    public static TaskManaService getInstance() {
        if (instance == null) {
            instance = new TaskManaService();
        }
        return instance;
    }

    /**
     * 获取任务信息
     * 
     * @param context
     * @param taskManaAdapter
     * @param lvTaskInfo
     */
    public void getTaskInfo(Context context,
            final TaskManaAdapter taskManaAdapter, final PullToRefreshListView lvTaskInfo) {
        String TASK_INFO_URL = Constant.URL + "task_info.json?pageNo="+pageNo;
        mQueue = Volley.newRequestQueue(context);
        final ArrayList<HashMap<String, Object>> listTask = new ArrayList<>();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(TASK_INFO_URL,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        if(response == null || response.length() == 0){
                            pageNo=1;
                        }else {
                            for (int i = 0; i < response.length(); i++) {
                                HashMap<String, Object> map = new HashMap<>();
                                try {

                                    JSONObject jObj = new JSONObject(
                                            response.get(i).toString());
                                    map.put("roomer_no", jObj.get("roomer_no"));
                                    map.put("roomer_name", jObj.get("roomer_name"));
                                    map.put("roomer_sex", jObj.get("roomer_sex"));
                                    map.put("roomer_phone_no",
                                            jObj.get("roomer_phone_no"));
                                    map.put("roomer_house_no",
                                            jObj.get("roomer_house_no"));
                                    map.put("roomer_date", jObj.get("roomer_date"));
                                    map.put("roomer_period",
                                            jObj.get("roomer_period"));
                                    map.put("roomer_rent", jObj.get("roomer_rent"));
                                    map.put("roomer_complete",
                                            jObj.get("roomer_complete"));
                                    map.put("roomer_emp_no",
                                            jObj.get("roomer_emp_no") == null ? ""
                                                    : jObj.get("roomer_emp_no"));
                                    map.put("house_city", jObj.get("house_city"));
                                    map.put("house_address",
                                            jObj.get("house_address"));
                                    listTask.add(map);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        totalData.addAll(listTask);
                        taskManaAdapter.setItemList(totalData);
                        if(pageNo == 1){
                            lvTaskInfo.setAdapter(taskManaAdapter);
                        }
                        pageNo++;
                        }
                        taskManaAdapter.notifyDataSetChanged();
                        Log.e("TAG", response.toString());
                    }
                }, new ErrorResponse(context));
        mQueue.add(jsonArrayRequest);
    }
}
