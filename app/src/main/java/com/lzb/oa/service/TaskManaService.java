package com.lzb.oa.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzb.oa.commons.Constant;
import com.lzb.oa.entity.TaskInfoEntity;
import com.lzb.oa.service.handler.DealTaskHandler;
import com.lzb.oa.service.handler.GetTaskInfosHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by lvzhenbin on 2015/10/9.
 */
public class TaskManaService {

    private static final String TAG = "TaskManaService";
    public static TaskManaService instance;
    private RequestQueue mRequestQueue;


    public static TaskManaService getInstance() {
        if (instance == null) {
            instance = new TaskManaService();
        }
        return instance;
    }


    /**
     * 获取任务信息
     * @param context
     * @param getTaskInfosHandler 回调方法
     */
    public void getTaskInfos(Context context, final GetTaskInfosHandler getTaskInfosHandler) {

        String TASK_INFO_URL = Constant.URL + "getNotHaveTasks.json";
        mRequestQueue = Volley.newRequestQueue(context);
        StringRequest mStringRequest = new StringRequest(TASK_INFO_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson mGson = new Gson();
                        List<TaskInfoEntity> tempTaskInfo = mGson.fromJson(response, new TypeToken<List<TaskInfoEntity>>() {
                        }.getType());
                        getTaskInfosHandler.onSuccess(tempTaskInfo);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, volleyError + "");
            }
        });
        mRequestQueue.add(mStringRequest);
    }

    /**
     * 取消所选的任务
     * @param context
     * @param operater
     * @param cancelTaskHandler
     */
    public void cancelTask(final Context context, JSONObject operater, final DealTaskHandler cancelTaskHandler){
        String CANCEL_TASK_URL = Constant.URL+ "task/cancel_task.json";
        mRequestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequestCancel = new JsonObjectRequest(
                CANCEL_TASK_URL, operater, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    int success = Integer.parseInt(response.getString("success"));
                    if (success == 1) {
                        cancelTaskHandler.onSuccess(1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,
                        "任务取消失败，请耐心等待5秒后再试！", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        mRequestQueue.add(jsonObjectRequestCancel);
    }

    public void selectTask(final Context context, JSONObject operater, final DealTaskHandler selectTaskHandler){
        String CANCEL_TASK_URL = Constant.URL+ "task/get_task.json";
        mRequestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequestCancel = new JsonObjectRequest(
                CANCEL_TASK_URL, operater, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    int success = Integer.parseInt(response.getString("success"));
                    if (success == 1) {
                        selectTaskHandler.onSuccess(1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,
                        "任务功领失败，请耐心等待5秒后再试！", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        mRequestQueue.add(jsonObjectRequestCancel);
    }

    /**
     * 取消所有或部分未完成的网络请求
     */
    public void cancelPendingRequests(){
        if(mRequestQueue != null){
            mRequestQueue.cancelAll(new Object());
        }
    }

}
