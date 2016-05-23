package com.lzb.oa.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.lzb.oa.commons.Constant;
import com.lzb.oa.entity.MeetingEntity;
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
    public static CompanyService getInstance() {
        if (instance == null) {
            instance = new CompanyService();
        }
        return instance;
    }


    public void getMeetingInfo(Context context,  final MeetingAdapter meetingAdapter){
        Volley.newRequestQueue(context);
        final String MEETING_URL = Constant.URL + "meeting_info.json";
        final ArrayList<MeetingEntity> listMeetingInfo = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                MEETING_URL, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.e("jsonArray", response.toString());
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject json = new JSONObject(response
                                .get(i).toString());
                        MeetingEntity meetingEntity = new MeetingEntity();
                        meetingEntity.setTheme(json.get("meeting_theme").toString());
                        meetingEntity.setTime(json.get("meeting_time").toString());
                        meetingEntity.setContent(json.get("meeting_content").toString());
                        meetingEntity.setAddress(json.get("meeting_address").toString());
                        listMeetingInfo.add(meetingEntity);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                meetingAdapter.setMeetings(listMeetingInfo);
            }
        }, new ErrorResponse(context));
        mQueue.add(jsonArrayRequest);

    }



}
