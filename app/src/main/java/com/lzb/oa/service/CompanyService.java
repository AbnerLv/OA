package com.lzb.oa.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lzb.oa.commons.Constant;
import com.lzb.oa.entity.MeetingEntity;
import com.lzb.oa.service.handler.GetMeetingInfosHandler;
import com.lzb.oa.service.response.ErrorResponse;
import com.lzb.oa.ui.adapter.MeetingAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by lzb on 2016/5/23.
 */
public class CompanyService {

    public static CompanyService instance;
    private RequestQueue mQueue = null;
    private final String TAG = "CompanyService";
    public static CompanyService getInstance() {
        if (instance == null) {
            instance = new CompanyService();
        }
        return instance;
    }


    public void getMeetingInfo(Context context, final GetMeetingInfosHandler getMeetingInfosHandler){
        Volley.newRequestQueue(context);
        final String MEETING_URL = Constant.URL + "get_meetting_info.json";
        mQueue = Volley.newRequestQueue(context);
        StringRequest mStringRequest = new StringRequest(MEETING_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        getMeetingInfosHandler.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, volleyError + "");
            }
        });
        mQueue.add(mStringRequest);

    }



}
