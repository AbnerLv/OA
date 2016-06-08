package com.lzb.oa.service;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import com.lzb.oa.service.response.ErrorResponse;
import com.lzb.oa.ui.auth.LoginActivity;

import org.json.JSONObject;

import java.lang.reflect.Type;
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
     * @param username
     *            用户名
     * @param password
     *            密码
     */
    public void modifyPassword(final Context context, JSONObject json,
            final String username, final String password) {
        String CHANGE_PASS_URL = Constant.URL + "change_pass.json";
        RequestQueue mQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                CHANGE_PASS_URL, json, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int success = Integer.parseInt(response
                                    .getString("success").toString().trim());
                            if (success == 1) {
                                dialog(username, password, context);
                            } else {
                                Toast.makeText(context, "旧密码不正确！",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new ErrorResponse(context));

        mQueue.add(jsonObjectRequest);

    }

    /**
     * 弹出对话框
     *
     * @param username
     *            用户名
     * @param password
     *            密码
     */
    public void dialog(final String username, final String password,
            final Context context) {

        SharedPreferences sp = context.getSharedPreferences("OAEmpInfo",
                context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("密码已修改成功，请重新登录！");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
               // LoginActivity.startLoginActivity(context, username, password);
            }
        });
        builder.setCancelable(false);
        builder.create().show();
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
