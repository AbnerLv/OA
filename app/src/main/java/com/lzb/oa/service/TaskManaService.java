package com.lzb.oa.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lzb.oa.commons.Constant;
import com.lzb.oa.entity.TaskInfo;
import com.lzb.oa.service.handler.GetTaskInfosHandler;
import com.lzb.oa.service.request.GsonRequest;
import com.lzb.oa.service.response.ErrorResponse;
import com.lzb.oa.ui.adapter.TaskManaAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lvzhenbin on 2015/10/9.
 */
public class TaskManaService {

    private static final String TAG = "TaskManaService";
    public static TaskManaService instance;
    private RequestQueue mQueue;
    private ArrayList<HashMap<String, Object>> totalData = new ArrayList<>();

    private int pageNo = 1;//设置pageNo的初始化值为1，即默认获取的是第一页的数据。

    public static TaskManaService getInstance() {
        if (instance == null) {
            instance = new TaskManaService();
        }
        return instance;
    }


    /**
     * 获取任务信息
     * @param context
     * @return
     */
    public void getTaskInfos(Context context, final GetTaskInfosHandler getTaskInfosHandler) {

        String TASK_INFO_URL = Constant.URL + "task_info.json?pageNo="+pageNo;
        mQueue = Volley.newRequestQueue(context);
        StringRequest mStringRequest = new StringRequest(TASK_INFO_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response == null || "".equals(response)){
                            pageNo = 1;
                        }else {
                            pageNo++;
                            getTaskInfosHandler.onSuccess(response);
                        }
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
