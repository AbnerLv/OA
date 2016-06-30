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
import com.lzb.oa.service.handler.GetHaveTasksHandler;
import com.lzb.oa.service.handler.ModifyPasswordHandler;
import com.lzb.oa.service.response.ErrorResponse;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by lvzhenbin on 2015/10/11.
 */
public class SettingService {

    private final String TAG = "SettingService";
    public static SettingService instance;
    private RequestQueue mRequestQueue;

    public static SettingService getInstance() {
        if (instance == null) {
            instance = new SettingService();
        }
        return instance;
    }

    /**
     * 修改密码
     * 
     * @param context
     *            ChangePasswdActivity.java
     * @param json
     *            username , password json 文件
     */
    public void modifyPassword(final Context context, JSONObject json,final ModifyPasswordHandler modifyPasswordHandler) {
        String MODIFY_PASSWORD_URL = Constant.URL + "modifyPassword.json";
        mRequestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                MODIFY_PASSWORD_URL, json, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int code = response.getInt("code");
                            modifyPasswordHandler.onSuccess(code);
                        } catch (Exception e) {
                            Toast.makeText(context,e.getMessage()+"",Toast.LENGTH_LONG).show();
                        }
                    }
                }, new ErrorResponse(context));

        mRequestQueue.add(jsonObjectRequest);

    }


    /**
     * 获取已经领取的任务
     * @param context
     * @param empNo
     * @param getHaveTasksHandler
     */
    public void getHaveTasks(Context context, String empNo, final GetHaveTasksHandler getHaveTasksHandler){
        final String HAVE_TASK_INFO_URL = Constant.URL + "getHaveTasks.json?emp_no="+empNo;
        mRequestQueue = Volley.newRequestQueue(context);
        StringRequest mStringRequest = new StringRequest(HAVE_TASK_INFO_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        List<TaskInfoEntity> entitys = gson.fromJson(response, new TypeToken<List<TaskInfoEntity>>() {}.getType());
                        getHaveTasksHandler.success(entitys);
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
     * 取消所有或部分未完成的网络请求
     */
    public void cancelPendingRequests(){
        if(mRequestQueue != null){
            mRequestQueue.cancelAll(new Object());
        }
    }

}
