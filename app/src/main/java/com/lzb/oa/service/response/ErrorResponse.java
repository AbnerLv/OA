package com.lzb.oa.service.response;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by lvzhenbin on 2015/10/9.
 */
public class ErrorResponse implements Response.ErrorListener {
    private final static int NET_ERROR_COMMONS = 1;
    private final static int NET_ERROR_CANCEL_TASK = 2;
    private final static int NET_ERROR_GET_TASK = 3;
    private Context context;
    private String message;

    public ErrorResponse(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        Toast.makeText(context, "网络错误，请稍后再试", Toast.LENGTH_LONG).show();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(int m) {
        String temp = "";
        switch (m) {
        case NET_ERROR_COMMONS:
            temp = "网络错误，请稍后再试";
            break;
        case NET_ERROR_CANCEL_TASK:
            temp = "任务取消失败，请耐心等待5秒后再试！";
            break;
        case NET_ERROR_GET_TASK:
            temp = "领取任务失败，请耐心等待5秒后再尝试！";
            break;
        }
        this.message = temp;
    }
}
