package com.lzb.oa.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lzb.oa.MainActivity;
import com.lzb.oa.cache.EmpInfoCache;
import com.lzb.oa.commons.Constant;
import com.lzb.oa.service.handler.CheckLoginHandler;
import com.lzb.oa.service.handler.ForgetPasswordHandler;
import com.lzb.oa.service.handler.ModifyPerInfoHandler;
import com.lzb.oa.service.handler.RegisterHandler;
import com.lzb.oa.service.response.ErrorResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lvzhenbin on 2015/10/4.
 */
public class UserService {

    public static UserService instance;
    private RequestQueue mRequestQueue;


    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void register(final Context context, final JSONObject json, final RegisterHandler handler) {
        final String REGISTER_URL = Constant.URL + "register.json";
        mRequestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                REGISTER_URL, json, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    int success = Integer.parseInt(response
                            .getString("success"));
                    if (success == 1) {
                        handler.register(success);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        mRequestQueue.add(jsonObjectRequest);

    }

    /**
     * 登陆验证
     */
    public void checkLogin(final Context context, JSONObject json, final CheckLoginHandler checkLoginHandler) {
        String LOGIN_URL = Constant.URL + "userLogin.json";
        mRequestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, LOGIN_URL, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String password = response.getString("emp_password").toString();
                            checkLoginHandler.onSuccess(password);
                            EmpInfoCache.cacheEmpInfo(context, response);
                        } catch (JSONException e) {
                            Toast.makeText(context, ""+e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                }, new ErrorResponse(context));
        mRequestQueue.add(jsonObjectRequest);
    }


    /**
     * 忘记密码
     *
     * @param context
     * @param json
     * @param forgetPasswordHandler
     */
    public void forgetPassword(final Context context, JSONObject json, final ForgetPasswordHandler forgetPasswordHandler) {
        String BACK_PASSWORD_URL = Constant.URL + "forgetPassword.json";
        mRequestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BACK_PASSWORD_URL, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String password = response.get("message").toString();
                            forgetPasswordHandler.success(password);
                        } catch (JSONException e) {
                            Toast.makeText(context, e.getMessage() + "", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage() + "", Toast.LENGTH_LONG).show();
            }
        });
        mRequestQueue.add(jsonObjectRequest);
    }

    /**
     * 修改个人信息
     *
     */
    public void modifyPerInfo(final Context context, JSONObject json, final ModifyPerInfoHandler modifyPerInfoHandler) {
        String MODIFY_PASSWORD_URL = Constant.URL + "modifyPerInfo.json";
        mRequestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                MODIFY_PASSWORD_URL, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int code = response.getInt("code");
                    modifyPerInfoHandler.onSuccess(code);
                } catch (Exception e) {
                    Toast.makeText(context,e.getMessage()+"",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }, new ErrorResponse(context));

        mRequestQueue.add(jsonObjectRequest);

    }


    /**
     * 取消所有或部分未完成的网络请求
     */
    public void cancelPendingRequests() {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(new Object());
        }
    }


}
