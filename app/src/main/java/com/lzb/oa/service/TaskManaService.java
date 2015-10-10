package com.lzb.oa.service;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.lzb.oa.commons.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lvzhenbin on 2015/10/9.
 */
public class TaskManaService {
    private final static String TASK_INFO_URL = Constant.URL + "task_info.json";
    public static TaskManaService instance;

    public static TaskManaService getInstance() {
        if (instance == null) {
            instance = new TaskManaService();
        }
        return instance;
    }

    /*
     * 向服务端发出请求
     */
    public ArrayList<HashMap<String, Object>> sendToServer(
            RequestQueue mQueue) {
        final ArrayList<HashMap<String, Object>> listTask = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(TASK_INFO_URL,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
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
                                        jObj.get("roomer_emp_no"));
                                map.put("house_city", jObj.get("house_city"));
                                map.put("house_address",
                                        jObj.get("house_address"));
                                listTask.add(map);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.e("TAG", response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        mQueue.add(jsonArrayRequest);
        return listTask;
    }
}
