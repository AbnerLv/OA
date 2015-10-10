package com.lzb.oa.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lzb.oa.MainActivity;
import com.lzb.oa.commons.Constant;
import com.lzb.oa.service.response.ErrorResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lvzhenbin on 2015/10/4.
 */
public class AuthService {

    public static AuthService instance;

    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    public void register(final Context context, final JSONObject json) {

    }

    /**
     * 登陆验证
     * 
     * @param json
     *            username,password 封装json文件
     * @param context
     *            LoginActivity.this
     */
    public void checkLogin(JSONObject json, final Context context) {
        String LOGIN_URL = Constant.URL + "login.json";
        RequestQueue mQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, LOGIN_URL, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                        String password = null;
                        try {
                            password = response.getString("emp_password")
                                    .toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (password == null || "0".equals(password)) {
                            Toast.makeText(context, "输入的用户名或密码有错",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            progressDialog(context);
                        }
                    }
                }, new ErrorResponse(context));

        mQueue.add(jsonObjectRequest);
    }

    /**
     * 登陆进程框
     * 
     * @param context
     *            LoginActivity.this
     */
    private void progressDialog(final Context context) {
        final ProgressDialog proDialog = new ProgressDialog(context);
        proDialog.setTitle("验证中");
        proDialog.setMessage("正在登陆，请稍后…");
        proDialog.setIndeterminate(true);
        proDialog.setCancelable(false);
        proDialog.show();

        final Timer t = new Timer();
        t.schedule(new TimerTask() {

            @Override
            public void run() {
                proDialog.dismiss();
                t.cancel();
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        }, 3000);
    }

}
