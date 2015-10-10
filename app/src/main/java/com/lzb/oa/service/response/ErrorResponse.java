package com.lzb.oa.service.response;

import android.app.Activity;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by lvzhenbin on 2015/10/9.
 */
public class ErrorResponse implements Response.ErrorListener {
    private Activity activity;

    public ErrorResponse(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        Toast.makeText(activity, "网络错误，请稍后再试", Toast.LENGTH_LONG).show();
    }

}
